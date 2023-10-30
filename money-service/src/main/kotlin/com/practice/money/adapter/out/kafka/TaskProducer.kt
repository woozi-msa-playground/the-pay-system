package com.practice.money.adapter.out.kafka

import com.fasterxml.jackson.databind.ObjectMapper
import com.practice.common.RechargingMoneyTask
import com.practice.money.application.port.out.RechargingMoneyTaskPort
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerRecord
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.Properties

@Component
class TaskProducer(
    @Value("\${kafka.clusters.bootstrapservers}") private val bootstrapServers: String,
    @Value("\${task.topic}") private val topic: String,
    private val objectMapper: ObjectMapper
) : RechargingMoneyTaskPort {

    private lateinit var producer: KafkaProducer<String, String>

    init {
        val properties = Properties()
        properties["bootstrap.servers"] = bootstrapServers
        properties["key.serializer"] = "org.apache.kafka.common.serialization.StringSerializer"
        properties["value.serializer"] = "org.apache.kafka.common.serialization.StringSerializer"
        this.producer = KafkaProducer<String, String>(properties)
    }

    override fun sendRechargingMoneyTaskPort(task: RechargingMoneyTask) {
        this.sendMessage(task.taskId, task)
    }

    fun sendMessage(key: String, value: RechargingMoneyTask) {
        val producerRecord = ProducerRecord(topic, key, objectMapper.writeValueAsString(value))
        println("Send Message")
        producer.send(producerRecord) { metadata, exception ->
            if (exception == null) {
                println("success = $metadata")
            } else {
                println("error = ${exception.printStackTrace()}")
            }
        }
    }
}