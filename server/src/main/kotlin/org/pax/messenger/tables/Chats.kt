package org.pax.messenger.tables

import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import org.pax.messenger.model.enumerated.ChatType
import java.util.UUID

object Chats : UUIDTable("chat") {
    val type = enumeration<ChatType>("type")
    val name = varchar("name", 255)
    val picture = varchar("picture", 255)
    val description = varchar("description", 512).nullable()
    val linkName = varchar("link_name", 512).nullable()
}

class Chat(id: EntityID<UUID>) : UUIDEntity(id) {
    var type by Chats.type
    var name by Chats.name
    var picture by Chats.picture
    var description by Chats.description
    var linkName by Chats.linkName

    companion object : UUIDEntityClass<Chat>(Chats)
}