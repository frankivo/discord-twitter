package com.github.frankivo

case class Image(url: String, username: String) {
  val tweet = s"${username} posted: ${url}"
}
