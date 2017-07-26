package com.github.raydive.metrics

import org.scalatra.test.specs2._

class RubyLikeAbcMetricSpec extends ScalatraSpec {
  def is =
    s2"""
    RubyLikeAbcMetric specification

  """.stripMargin

  def code1 = {
    // TODO: Make Test
    val value =
      """
        word = 1
        if word == 1
          test(word)
        end
        word = test + 1
      """
  }
}
