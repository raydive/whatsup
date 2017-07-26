package com.github.raydive.metrics

import com.github.raydive.parser._

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
      case Right(result) => Right(1) // TODO: Implement
      case Left(error) => Left(error)
    }
  }
}

