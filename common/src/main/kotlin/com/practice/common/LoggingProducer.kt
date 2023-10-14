package com.practice.common

import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerRecord
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.Properties

@Component
class LoggingProducer(
    @Value("\${kafka.clusters.bootstrapservers}") private val bootstrapServers: String,
    @Value("\${logging.topic}") private val topic: String,
) {

    private lateinit var producer: KafkaProducer<String, String>

    init {
        val properties = Properties()
        properties["bootstrap.servers"] = bootstrapServers
        properties["key.serializer"] = "org.apache.kafka.common.serialization.StringSerializer"
        properties["value.serializer"] = "org.apache.kafka.common.serialization.StringSerializer"
        this.producer = KafkaProducer<String, String>(properties)
    }

    fun sendMessage(key: String, value: String) {
        val producerRecord = ProducerRecord(topic, key, value)
        producer.send(producerRecord) { metadata, exception ->
            if (exception == null) {
                println("success = $metadata")
            } else {
                println("error = ${exception.printStackTrace()}")
            }
        }
    }
}