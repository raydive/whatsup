package com.github.raydive.metrics

import org.scalatra.test.specs2._

import scala.math.sqrt

class RubyLikeAbcMetricSpec extends ScalatraSpec {
  def is =
    s2"""
      RubyLikeAbcMetric specification

      code1のABC metricが正しい $code1
      code2のABC metricが正しい $code2
    """

  def code1 = {
    val value =
      """
        word = 1
        if word == 1
          test(word)
        end
        word = test + 1
      """

    RubyLikeAbcMetric.calculate(value) match {
      case Right(abc) => abc must_== sqrt(6.0).toInt
    }
  }

  def code2 = {
    val value =
      """
        word = 1
        test = 2 + word
        if word == 1
          unless test != 2
            word += test
          end
        end
        print(test)
      """

    RubyLikeAbcMetric.calculate(value) match {
      case Right(abc) => abc must_== sqrt(3 * 3 + 2 * 2 + 1 * 1).toInt
    }
  }
}
