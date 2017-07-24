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
      case Right(result) =>
        result.flat_map
        Right(1)
      case Left(error) => Left(error)
    }
  }
}

