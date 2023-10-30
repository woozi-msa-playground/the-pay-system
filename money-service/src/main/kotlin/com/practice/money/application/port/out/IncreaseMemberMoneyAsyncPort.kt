package com.practice.money.application.port.out

import com.practice.money.domain.MemberMoneyChangingRequest

fun interface IncreaseMemberMoneyAsyncPort {
    fun increaseMoneyPort(memberMoneyChangingRequestId: MemberMoneyChangingRequest): MemberMoneyChangingRequest
}