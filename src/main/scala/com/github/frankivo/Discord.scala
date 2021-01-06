package com.github.frankivo

import discord4j.core.DiscordClient
import discord4j.core.event.domain.message.MessageCreateEvent

object Discord {
  val TOKEN: String = sys.env("DISCORD_TOKEN")
}

class Discord {
  connect()

  private def connect(): Unit = {
    val client = DiscordClient.create(Discord.TOKEN)
    val gateway = client.login().block()

    gateway.on(classOf[MessageCreateEvent])
      .toIterable
      .forEach(println)
  }
}