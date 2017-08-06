package com.github.raydive.requests

import org.json4s.JsonAST.JObject

case class Topic(id: Long,
                 name: String,
                 isDirectMessage: Boolean,
                 lastPostedAt: Option[java.util.Date],
                 createdAt: Option[java.util.Date],
                 updatedAt: Option[java.util.Date])

case class Post(id: Long,
                topicId: Long,
                replyTo: Option[Long],
                message: String,
                account: Account,
                mention: Option[JObject],
                attachments: List[JObject],
                likes: List[JObject],
                talks: List[JObject],
                links: List[JObject],
                createdAt: Option[java.util.Date],
                updatedAt: Option[java.util.Date])

case class Account(id: Long,
                   name: String,
                   fullName: String,
                   suggestion: String,
                   imageUrl: String,
                   isBot: Boolean,
                   createdAt: Option[java.util.Date],
                   updatedAt: Option[java.util.Date])

case class JsonRequest(topic: Topic,
                       post: Post,
                       mentions: List[JObject],
                       exceedsAttachmentLimit: Boolean,
                       directMessage: Option[JObject])


