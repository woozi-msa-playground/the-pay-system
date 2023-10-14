package com.practice.logging

import org.apache.kafka.clients.consumer.KafkaConsumer
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.time.Duration
import java.util.Collections
import java.util.Properties

@Component
class LoggingConsumer(
    @Value("\${kafka.clusters.bootstrapservers}") private val bootstrapServers: String,
    @Value("\${logging.topic}") private val topic: String,
) {

    private lateinit var consumer: KafkaConsumer<String, String>

    init {
        val properties = Properties()
        properties["bootstrap.servers"] = bootstrapServers
        properties["group.id"] = "logging-group"
        properties["key.deserializer"] = "org.apache.kafka.common.serialization.StringDeserializer"
        properties["value.deserializer"] = "org.apache.kafka.common.serialization.StringDeserializer"
        this.consumer = KafkaConsumer<String, String>(properties)
        consumer.subscribe(Collections.singleton(topic))


        try {
            while (true) {
                val records = consumer.poll(Duration.ofSeconds(1))
                for (record in records) {
                    println("Consume Topic Data = $record")
                }
            }
        } finally {
            consumer.close()
        }
    }
}