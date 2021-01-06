package com.github.frankivo

import discord4j.common.util.Snowflake
import discord4j.core.DiscordClient
import discord4j.core.event.domain.message.MessageCreateEvent

import scala.jdk.OptionConverters._

object Discord {
  val TOKEN: String = sys.env("DISCORD_TOKEN")

  val CHANNELS: Seq[Long] = {
    sys.env("DISCORD_CHANNELS")
      .split(",")
      .map(_.toLong)
  }

  def hasChannel(channel: Snowflake): Boolean = CHANNELS.contains(channel.asLong())
}

class Discord {
  connect()

  private def connect(): Unit = {
    val client = DiscordClient.create(Discord.TOKEN)
    val gateway = client.login().block()

    gateway.on(classOf[MessageCreateEvent])
      .filter(e => Discord.hasChannel(e.getMessage.getChannelId))
      .toIterable
      .forEach(e => {
        e.getMessage.getAttachments.forEach(a => publish(Image(a.getUrl, username(e))))
      })
  }

  private def username(event: MessageCreateEvent): String = {
    event
      .getMember
      .toScala
      .flatMap(m => Some(m.getDisplayName))
      .getOrElse("Unknown user")
  }

  private def publish(img: Image): Unit = println(img)
}
