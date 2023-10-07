package com.practice.msa

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MembershipServiceApplication

fun main(args: Array<String>) {
    runApplication<MembershipServiceApplication>(*args)
}
