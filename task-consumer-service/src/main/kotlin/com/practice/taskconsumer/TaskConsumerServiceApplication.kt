package com.practice.taskconsumer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TaskConsumerServiceApplication

fun main(args: Array<String>) {
    runApplication<TaskConsumerServiceApplication>(*args)
}
