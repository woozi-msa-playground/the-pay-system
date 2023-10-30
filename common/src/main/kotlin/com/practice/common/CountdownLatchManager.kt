package com.practice.common

import org.springframework.stereotype.Component
import java.util.concurrent.CountDownLatch

@Component
class CountdownLatchManager(
    private val countDownLatchMap: MutableMap<String, CountDownLatch> = HashMap(),
    private val stringMap: MutableMap<String, String> = HashMap(),
) {

    fun addCountDownLatch(key: String) {
        countDownLatchMap[key] = CountDownLatch(1)
    }

    fun countDownLatch(key: String): CountDownLatch {
        return countDownLatchMap[key]!!
    }

    fun addDataForKey(key: String, value: String) {
        stringMap[key] = value
    }

    fun dataForKey(key: String): String =
        stringMap[key]!!
}