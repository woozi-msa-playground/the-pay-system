package com.practice.money

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory

@Configuration
class MoneyKafkaConfiguration(
    @Value("\${kafka.clusters.bootstrapservers}") private val bootstrapServers: String,
) {

    @Primary
    @Bean
    fun kafkaListenerContainerFactory(): ConcurrentKafkaListenerContainerFactory<String, Any> {
        val concurrentKafkaListenerContainerFactory = ConcurrentKafkaListenerContainerFactory<String, Any>()
        concurrentKafkaListenerContainerFactory.consumerFactory = moneyConsumerFactory()
        return concurrentKafkaListenerContainerFactory
    }

    @Bean
    fun moneyConsumerFactory(): DefaultKafkaConsumerFactory<String, Any> {
        val config = hashMapOf<String, Any>()
        config["bootstrap.servers"] = bootstrapServers
        config["key.deserializer"] = "org.apache.kafka.common.serialization.StringDeserializer"
        config["value.deserializer"] = "org.apache.kafka.common.serialization.StringDeserializer"
        return DefaultKafkaConsumerFactory<String, Any>(config)
    }
}
