package com.github.frankivo

import akka.actor.Actor
import twitter4j.{StatusUpdate, TwitterFactory}

class Twitter extends Actor {
  override def receive: Receive = {
    case img: Image => tweet(img)
  }

  private val twitter = TwitterFactory.getSingleton

  private def tweet(img: Image): Unit = {
    println("tweet!")

    val update = new StatusUpdate(img.tweet)
    val file = img.file
    update.setMedia(file)

    val status = twitter.updateStatus(update)
    println(s"Tweeted: ${status.getText}")

    file.delete
  }
}
