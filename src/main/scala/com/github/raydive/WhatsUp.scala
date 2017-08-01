package com.github.raydive

class WhatsUp extends WhatsUpStack {
  get("/") {
    contentType="text/html"
    jade("/index")
  }
}
