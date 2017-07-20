package com.github.raydive

import org.scalatra._

class Hello extends WhatsUpStack {

  get("/") {
    <p>Hello! Who is speaking please?</p>
  }

}
