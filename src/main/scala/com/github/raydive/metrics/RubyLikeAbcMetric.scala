package com.github.raydive.metrics

import com.github.raydive.parser._

import scala.math.sqrt

/**
  * abc metric
  * http://wiki.c2.com/?AbcMetric
  *
  * Assignment
  * Branch
  * Condition
  */
object RubyLikeAbcMetric {
  def calculate(code: String) : Either[String, Int] = {
    RubyLikeParser(code) match {
      case Right(result) =>
        var a = 0
        var b = 0
        var c = 0

        for (
          line <- result
        ) {
          line match {
            case Line(_: Condition) => c += 1
            case Line(_: Call) => b += 1
            case Line(token: Expression) =>
              token match {
                case Expression(_: Call, _, _: Call) => b += 2
                case Expression(_: Call, _, _: Variable) => b += 1
                case Expression(_: Variable, _, _: Call) => b += 1
                case _ => 0
              }
            case Line(token: Statement) =>
              a += 1
              token match {
                case Statement(_, _, ecv) =>
                  ecv match {
                    case Call(_) => b += 1
                    case Expression(_: Call, _, _: Call) => b += 2
                    case Expression(_: Call, _, _: Variable) => b += 1
                    case Expression(_: Variable, _, _: Call) => b += 1
                    case _ => 0
                  }
              }
            case _ => 0
          }
        }

        Right(sqrt(a * a + b * b + c * c).toInt)
      case Left(error) => Left(error)
    }
  }
}

