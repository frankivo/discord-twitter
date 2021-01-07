package com.github.frankivo

import akka.actor.{ActorRef, ActorSystem, Props}

object DiscordTwitterBot {

  private val AKKA: ActorSystem = ActorSystem()
  val ACTOR_TWITTER: ActorRef = AKKA.actorOf(Props(new Twitter))

  def main(args: Array[String]): Unit = {
    println("Hello, Ryan")

    new Discord
  }
}
