package com.github.frankivo

import discord4j.core.DiscordClient

object DiscordTwitter {
  val DISCORD_TOKEN: String = sys.env("DISCORD_TOKEN")

  def main(args: Array[String]): Unit = {
    println("Hello, Ryan")


    val client = DiscordClient.create(DISCORD_TOKEN)
  }
}
