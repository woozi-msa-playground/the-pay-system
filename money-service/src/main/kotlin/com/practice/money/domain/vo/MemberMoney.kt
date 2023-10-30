package com.practice.money.domain.vo

data class MemberMoney(
    val memberMoneyId: String,
    val membershipId: Long,
    val balance: Int,
    val aggregateIdentifier: String
)