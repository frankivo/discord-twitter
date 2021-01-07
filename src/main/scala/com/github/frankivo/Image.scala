package com.github.frankivo

import scalaj.http.Http

import java.io.{File, FileOutputStream}

case class Image(url: String, username: String) {
  val tweet = s"$username posted: on discord"

  def file: File = {
    val fileName = File.createTempFile("discord-twitter-", "")

    val result = Http(url).asBytes
    println(result.code)

    val out = new FileOutputStream(fileName)
    out.write(result.body)
    out.close()

    fileName
  }
}
