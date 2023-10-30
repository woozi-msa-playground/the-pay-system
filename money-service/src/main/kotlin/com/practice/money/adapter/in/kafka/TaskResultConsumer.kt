package com.practice.money.adapter.`in`.kafka

import com.fasterxml.jackson.databind.ObjectMapper
import com.practice.common.CountdownLatchManager
import com.practice.common.LoggingProducer
import com.practice.common.RechargingMoneyTask
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component
import java.util.Collections
import java.util.Properties

@Component
class TaskResultConsumer(
    @Value("\${kafka.clusters.bootstrapservers}") private val bootstrapServers: String,
    @Value("\${task.result.topic}") private val topic: String,
    private val objectMapper: ObjectMapper,
    private val loggingProducer: LoggingProducer,
    private val countDownLatchManager: CountdownLatchManager
) {

    private lateinit var consumer: KafkaConsumer<String, String>

    @KafkaListener(topics = ["\${task.result.topic}"], groupId = "money-consumer")
    fun listen(consumerRecordValue: String) {
        val rechargingMoneyTask = objectMapper.readValue(consumerRecordValue, RechargingMoneyTask::class.java)
        println("Listen Message$rechargingMoneyTask")
        val subTasks = rechargingMoneyTask.subTaskList
        if (subTasks.all { it.status == "success" }) {
            loggingProducer.sendMessage(rechargingMoneyTask.taskId, "task success")
            countDownLatchManager.addDataForKey(rechargingMoneyTask.taskId, "success")
        } else {
            loggingProducer.sendMessage(rechargingMoneyTask.taskId, "task failed")
            countDownLatchManager.addDataForKey(rechargingMoneyTask.taskId, "failed")
        }
        Thread.sleep(3000)
        countDownLatchManager.countDownLatch(rechargingMoneyTask.taskId).countDown()
    }
}