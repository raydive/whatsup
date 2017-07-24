package com.github.raydive.parser

import org.scalatra.test.specs2._

class RubyLikeParserSpec extends ScalatraSpec { def is =
  s"""
    Ruby like code parser specification
    wordはパースできること $word
  """.stripMargin

  def word = {
    val test1 = RubyLikeParser("word")
    println(test1)
    val test2 = RubyLikeParser("word = 1")
    println(test2)
    val test3 = RubyLikeParser("word = 1 + test")
    println(test3)
    val test4 = RubyLikeParser("test()")
    println(test4)
    val test5 = RubyLikeParser("test(a, b)")
    println(test5)
    val value1 =
      """
         word = 1
         word += 1
         test(word, 2)
         word = 1 + test
      """.stripMargin
    val test6 = RubyLikeParser(value1)
    println(test6)

    val value2 =
      """
        word = 1
        if word == 1
          test(word)
        end
        word = test + 1
      """.stripMargin
    val test7 = RubyLikeParser(value2)
    println(test7)
  }
}
