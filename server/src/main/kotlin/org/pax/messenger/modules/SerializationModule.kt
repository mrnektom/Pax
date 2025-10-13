package org.pax.messenger.modules

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.*
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import com.fasterxml.jackson.module.kotlin.addDeserializer
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.ktor.http.*
import io.ktor.serialization.jackson.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import kotlin.uuid.Uuid

fun Application.serializationModule() {
    install(ContentNegotiation) {
        val jsonObjectMapper = jacksonObjectMapper().apply {
            findAndRegisterModules()
            registerModule(SerializationModule)
            configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
            configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        }

        register(ContentType.Application.Json, JacksonConverter(jsonObjectMapper, true))
    }
}

private object SerializationModule : SimpleModule() {
    fun readResolve(): Any = SerializationModule

    init {
        addSerializer(UuidSerializer)
        addDeserializer(Uuid::class, UuidDeserializer)
    }
}

private object UuidSerializer : StdSerializer<Uuid>(Uuid::class.java) {
    fun readResolve(): Any = UuidSerializer

    override fun serialize(
        value: Uuid,
        gen: JsonGenerator,
        serializers: SerializerProvider
    ) {
        gen.writeString(value.toString())
    }

}

private object UuidDeserializer : JsonDeserializer<Uuid>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): Uuid {
        return Uuid.parse(p.valueAsString)
    }
}