package com.github.frankivo

import akka.actor.Actor
import twitter4j.TwitterFactory

class Twitter extends Actor {
  override def receive: Receive = {
    case text: String => tweet(text)
    case img: Image => tweet(img)
  }

  private val twitter = TwitterFactory.getSingleton

  private def tweet(img: Image) : Unit = {
    println(twitter.updateStatus("Test!").getText)
  }

  private def tweet(img: String) : Unit = {
    println(twitter.updateStatus(img).getText)
  }
}
