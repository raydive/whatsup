package com.github.raydive.parser

import org.scalatra.test.specs2._

class RubyLikeParserSpec extends ScalatraSpec {
  def is =
    s2"""
    Ruby like code parser specification
      変数のみはパースできること $variable
      変数への代入はパースできること $transfer
      変数への代入（式をふくむ）はパースできること $transfer2
      変数への代入（関数をふくむ）はパースできること1 $transfer3
      変数への代入（関数をふくむ）はパースできること2 $transfer4
  """

  def variable = {
    RubyLikeParser("word") match {
      case Right(word) => word === List(Line(Variable("word")))
    }
  }

  def transfer = {
    RubyLikeParser("word = 1") match {
      case Right(t) =>
        t === List(
          Line(Statement(Variable("word"), Transfer("="), Variable("1"))))
    }
  }

  def transfer2 = {
    RubyLikeParser("word = 1 + test") match {
      case Right(t) =>
        t === List(
          Line(
            Statement(
              Variable("word"),
              Transfer("="),
              Expression(Variable("1"), Operator("+"), Variable("test")))))
    }
  }

  def transfer3 = {
    RubyLikeParser("word = test + func(1)") match {
      case Right(t) =>
        t === List(
          Line(
            Statement(
              Variable("word"),
              Transfer("="),
              Expression(Variable("test"), Operator("+"), Call("func(1)")))))
    }
  }

  def transfer4 = {
    RubyLikeParser("word = func(1)+ test") match {
      case Right(t) =>
        t === List(
          Line(
            Statement(
              Variable("word"),
              Transfer("="),
              Expression(Variable("test"), Operator("+"), Call("func(1)")))))
    }
  }
}
