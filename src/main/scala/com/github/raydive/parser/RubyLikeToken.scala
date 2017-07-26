package com.github.raydive.parser

sealed trait WorkToken extends Product with Serializable

case class Line(token: WorkToken)

case class Statement(word: WorkToken, tr: Transfer, tk: WorkToken) extends WorkToken

case class Call(str: String) extends WorkToken

case class Expression(wc1: WorkToken, bin_on: Operator, wc2: WorkToken) extends WorkToken

case class Variable(word: String) extends WorkToken

case class Operator(str: String) extends WorkToken

case class Condition(str: String) extends WorkToken

case class Transfer(str: String) extends WorkToken


