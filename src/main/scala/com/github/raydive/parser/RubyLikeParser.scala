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

  def code: Parser[List[Line]] = rep(line)

  def line: Parser[Line] =
    (statement | condition | expression | call | variable) <~ opt(eol) ^^ {
      case line: WorkToken => Line(Seq(line))
    }
  def condition: Parser[Condition] = """^(if|unless).*""".r ^^ { Condition(_) }

  def statement: Parser[Statement] = variable ~ transfer ~ (expression | call | variable) ^^ {
    case wr ~ tr ~ any =>
      Statement(wr, tr, any)
  }
  def transfer: Parser[Transfer] = """[\+\-\*/]?=""".r ^^ { Transfer(_) }
  def call: Parser[Call] = """\w+\(.*\)""".r ^^ { Call(_) }

  def expression: Parser[Expression] = (call | variable) ~ binary_op ~ (call | variable) ^^ {
    case wc1 ~ bin_op ~ wc2 =>
      Expression(wc1, bin_op, wc2)
  }
  def binary_op: Parser[Operator] = """[\+\-\*/]""".r ^^ { Operator(_) }

  def unary_op: Parser[Operator] =
    """\+|\-""".r ^^ {
      Operator(_)
    }

  def variable: Parser[Variable] =
    """\w+""".r ^^ {
      Variable(_)
    }
  def eol: Parser[Option[Char]] = opt('\r') <~ '\n'

  def apply(input: String): Either[String, List[Line]] = parseAll(code, input) match {
    case Success(codeData, _) => Right(codeData)
    case NoSuccess(errorMessage, next) =>
      Left(
        s"$errorMessage on line ${next.pos.line} on column ${next.pos.column}")
  }
}
