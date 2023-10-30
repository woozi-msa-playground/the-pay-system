package com.practice.taskconsumer

import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerRecord
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.Properties

@Component
class TaskResultProducer(
    @Value("\${kafka.clusters.bootstrapservers}") private val bootstrapServers: String,
    @Value("\${task.result.topic}") private val topic: String,
    private val objectMapper: ObjectMapper
) {

    private lateinit var producer: KafkaProducer<String, String>

    init {
        val properties = Properties()
        properties["bootstrap.servers"] = bootstrapServers
        properties["key.serializer"] = "org.apache.kafka.common.serialization.StringSerializer"
        properties["value.serializer"] = "org.apache.kafka.common.serialization.StringSerializer"
        this.producer = KafkaProducer<String, String>(properties)
    }


    fun sendMessage(key: String, value: Any) {
        val producerRecord = ProducerRecord(topic, key, objectMapper.writeValueAsString(value))
        producer.send(producerRecord) { metadata, exception ->
            if (exception == null) {
                println("success = $metadata")
            } else {
                println("error = ${exception.printStackTrace()}")
            }
        }
    }
}