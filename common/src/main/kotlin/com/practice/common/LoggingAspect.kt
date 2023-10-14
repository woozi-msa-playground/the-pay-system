package com.practice.common

import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.springframework.stereotype.Component

@Aspect
@Component
class LoggingAspect(
    private val loggingProducer: LoggingProducer
) {

    @Before("execution(* com.practice.*.adapter.in.web.*.*(..))")
    fun beforeMethodExecution(joinPoint: JoinPoint) {
        val name = joinPoint.signature.name
        loggingProducer.sendMessage("logging", "Before executing method $name")
    }
}