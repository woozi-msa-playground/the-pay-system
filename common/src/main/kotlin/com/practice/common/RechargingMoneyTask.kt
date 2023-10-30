package com.practice.common

data class RechargingMoneyTask(
    val taskId: String,
    val taskName: String,
    val membershipId: String,
    val subTaskList: List<SubTask>,
    val toBankName: String,
    val toBankAccountNumber: String,
    val moneyAmount: Int,
)