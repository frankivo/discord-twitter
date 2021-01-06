package com.github.frankivo

import akka.actor.{ActorRef, ActorSystem, Props}

object DiscordTwitterBot {

  val AKKA: ActorSystem = ActorSystem()

  val ACTOR_TWITTER: ActorRef = AKKA.actorOf(Props(new Twitter))
  val ACTOR_DISCORD: ActorRef = AKKA.actorOf(Props(new Discord))

  def main(args: Array[String]): Unit = {
    println("Hello, Ryan")
  }
}
