package com.github.raydive

import com.github.raydive.metrics.RubyLikeAbcMetric
import com.github.raydive.requests._
import com.github.raydive.responses._
import org.json4s.JsonAST.{JNothing, JObject}
import org.json4s.{DefaultFormats, Formats}
import org.scalatra.json._

class Abc extends WhatsUpStack with JacksonJsonSupport {
  protected implicit val jsonFormats: Formats = DefaultFormats

  before() {
    contentType = formats("json")
  }

  post("/") {
    parsedBody match {
      case JNothing => halt(400, "Invalid Json")
      case _: JObject => {
        val req = parsedBody.extract[JsonRequest]
        val message = req.post.message
        println(message)

        val code = message.drop(message.indexOf(" "))
        println(code)
        RubyLikeAbcMetric.calculate(code) match {
          case Right(result) =>
            TypetalkResponse(s"Abc metric: $result", req.post.account.id)
          case Left(result) => TypetalkResponse(result, req.post.account.id)
        }
      }
      case _ => halt(400, "Unknown Json")
    }
  }
}
