package com.practice.money

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MoneyServiceApplication

fun main(args: Array<String>) {
    runApplication<MoneyServiceApplication>(*args)
}
