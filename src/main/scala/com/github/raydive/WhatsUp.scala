package com.github.raydive

import org.scalatra._

class WhatsUp extends WhatsUpStack {

  get("/") {
    params
    contentType="text/html"

    jade("/index")
  }

  get("/abc") {

  }
}
