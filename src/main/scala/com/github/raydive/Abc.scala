package com.github.raydive

import com.github.raydive.metrics.RubyLikeAbcMetric
import org.json4s.JsonAST.{JNothing, JObject}
import org.json4s.{DefaultFormats, Formats}
import org.scalatra.json._
import org.slf4j.LoggerFactory

case class Topic(id: Long,
                 name: String,
                 suggestion: String,
                 lastPostedAt: Option[java.util.Date],
                 createdAt: Option[java.util.Date],
                 updatedAt: Option[java.util.Date])

case class Post(id: Long,
                topicId: Long,
                replyTo: Option[Long],
                message: String,
                account: Account,
                mention: JObject,
                attachemnts: List[JObject],
                likes: List[JObject],
                links: List[JObject],
                createdAt: Option[java.util.Date],
                updatedAt: Option[java.util.Date])

case class Account(id: Long,
                   name: String,
                   fullName: String,
                   suggestion: String,
                   imageUrl: String,
                   createdAt: Option[java.util.Date],
                   updatedAt: Option[java.util.Date])

case class JsonRequest(topic: Topic, post: Post)

case class AbcResponse(message: String, replyTo: Long)

class Abc extends WhatsUpStack with JacksonJsonSupport {
  protected implicit val jsonFormats: Formats = DefaultFormats

  val logger = LoggerFactory.getLogger(getClass)

  before() {
    contentType = formats("json")
  }

  post("/") {
    parsedBody match {
      case JNothing => halt(400, "Invalid Json")
      case json: JObject => {
        val req = parsedBody.extract[JsonRequest]
        logger.info(req.post.message)
        RubyLikeAbcMetric.calculate(req.post.message) match {
          case Right(result) =>
            AbcResponse(s"Abc metric: $result", req.post.account.id)
          case Left(result) => AbcResponse(result, req.post.account.id)
        }
      }
      case _ => halt(400, "Unknown Json")
    }
  }
}
