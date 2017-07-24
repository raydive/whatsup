package com.github.raydive.parser

import scala.util.parsing.combinator._

case class Function(str: String)
case class Operator(str: String)
case class Condition(str: String)
case class Transfer(str: String)

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
  def line = (condition | expression | function | statement | end | word) <~ opt(eol)
  def condition = """^(if|unless).*""".r ^^ { Condition(_) }
  def end = "end"

  def statement = word ~ transfer ~ (function | expression | word)
  def transfer  = """[\+\-\*/]?=""".r ^^ { Transfer(_) }
  def function  = """\w+\(.*\)""".r ^^ { Function(_) }
  def expression = (word | function).+ ~ binary_op ~ (word | function).+
  def binary_op = """[\+\-\*/]""".r ^^ { Operator(_) }
  def unary_op  = """[\+\-]""".r ^^ { Operator(_) }
  def word      = """\w+""".r
  def eol = opt('\r') <~ '\n'

  def apply(input: String): Either[String, Any] = parseAll(code, input) match {
    case Success(codeData, _) => Right(codeData)
    case NoSuccess(errorMessage, next) => Left(s"$errorMessage on line ${next.pos.line} on column ${next.pos.column}")
  }
}
