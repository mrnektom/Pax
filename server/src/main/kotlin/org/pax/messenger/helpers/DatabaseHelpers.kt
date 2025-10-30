package org.pax.messenger.helpers

import com.fasterxml.jackson.module.kotlin.jsonMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.json.jsonb

val objectMapper = jsonMapper()

inline fun <reified T : Any> Table.jsonb(name: String) = jsonb(
    name = name,
    serialize = { objectMapper.writeValueAsString(it) },
    deserialize = { objectMapper.readValue(it) }
)