package com.practice.logging

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class LoggingConsumerServiceApplication

fun main(args: Array<String>) {
    runApplication<LoggingConsumerServiceApplication>(*args)
}
