package com.github.frankivo

import discord4j.common.util.Snowflake
import discord4j.core.DiscordClient
import discord4j.core.event.domain.message.MessageCreateEvent

import scala.jdk.OptionConverters.RichOptional

object Discord {
  val TOKEN: String = sys.env("discord4j.token")
  val DEBUG: Boolean = sys.env.getOrElse("discord4j.debug", "false").toBoolean

  val CHANNELS: Seq[Long] = {
    sys.env.getOrElse("discord4j.channels", "")
      .split(",")
      .toIndexedSeq
      .flatMap(_.toLongOption)
  }

  def hasChannel(channel: Snowflake): Boolean = {
    if (CHANNELS.isEmpty) return true
    CHANNELS.contains(channel.asLong())
  }
}

class Discord {
  connect()

  private def connect(): Unit = {
    val client = DiscordClient.create(Discord.TOKEN)
    val gateway = client.login().block()

    gateway.on(classOf[MessageCreateEvent])
      .subscribe(e => handle(e))
  }

  def handle(event: MessageCreateEvent): Unit = {
    if (!Discord.hasChannel(event.getMessage.getChannelId))
      return

    if (Discord.DEBUG)
      println(event)

    event
      .getMessage
      .getAttachments
      .forEach(a => {
        publish(
          Image(a.getUrl, username(event), content(event))
        )
      })
  }

  private def username(event: MessageCreateEvent): String = {
    event
      .getMember
      .toScala
      .flatMap(m => Some(m.getDisplayName))
      .getOrElse("Unknown user")
  }

  private def content(event: MessageCreateEvent): String = {
    event
      .getMessage
      .getContent
  }

  private def publish(img: Image): Unit = DiscordTwitterBot.ACTOR_TWITTER ! img
}
