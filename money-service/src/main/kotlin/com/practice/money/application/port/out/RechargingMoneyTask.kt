package com.practice.money.application.port.out

import com.practice.common.RechargingMoneyTask

fun interface RechargingMoneyTaskPort {
    fun sendRechargingMoneyTaskPort(task: RechargingMoneyTask)
}