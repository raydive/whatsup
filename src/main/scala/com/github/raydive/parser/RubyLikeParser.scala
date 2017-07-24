package com.github.raydive.parser

import scala.util.parsing.combinator._

/*
    a = sum(1, 2)
    if a != 2
      print(a)
      a = 4
    end
    c = 1

    みたいなRubyっぽいコードをパースする
    ABC metricを計算するためだけに作った雑なパーサーなので、クラス等は未対応
 */
object RubyLikeParser extends RegexParsers {

  def code = rep(line)
  def line =
    (condition | expression | call | statement | end | word) <~ opt(eol)
  def condition = """^(if|unless).*""".r ^^ { Condition(_) }
  def end = "end"

  def statement = word ~ transfer ~ (call | expression | word) ^^ {
    case wr ~ tr ~ any => List(wr, tr, any)
  }
  def transfer = """[\+\-\*/]?=""".r ^^ { Transfer(_) }
  def call = """\w+\(.*\)""".r ^^ { Call(_) }
  def expression = (word | call) ~ binary_op ~ (word | call) ^^ {
    case wc1 ~ bin_op ~ wc2 => List(wc1, bin_op, wc2)
  }
  def binary_op = """[\+\-\*/]""".r ^^ { Operator(_) }
  def unary_op = """[\+\-]""".r ^^ { Operator(_) }
  def word = """\w+""".r
  def eol = opt('\r') <~ '\n'

  def apply(input: String): Either[String, Any] = parseAll(code, input) match {
    case Success(codeData, _) => Right(codeData)
    case NoSuccess(errorMessage, next) =>
      Left(
        s"$errorMessage on line ${next.pos.line} on column ${next.pos.column}")
  }
}
