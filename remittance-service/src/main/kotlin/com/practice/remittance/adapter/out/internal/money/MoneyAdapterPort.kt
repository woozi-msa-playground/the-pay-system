package com.practice.remittance.adapter.out.internal.money

import com.fasterxml.jackson.databind.ObjectMapper
import com.practice.common.CommonHttpClient
import com.practice.common.InternalSystemAdapter
import com.practice.remittance.application.port.out.money.MoneyInfo
import com.practice.remittance.application.port.out.money.RequestMoneyDecreasePort
import com.practice.remittance.application.port.out.money.RequestMoneyIncreasePort
import com.practice.remittance.application.port.out.money.RequestMoneyInfoPort
import com.practice.remittance.application.port.out.money.RequestMoneyRechargingPort
import com.practice.remittance.domain.vo.Amount
import com.practice.remittance.domain.vo.MembershipId
import org.springframework.beans.factory.annotation.Value

@InternalSystemAdapter
class MoneyAdapterPort(
    private val commonHttpClient: CommonHttpClient,
    private val objectMapper: ObjectMapper,
    @Value("\${service.money.url}") private val moneyServiceUrl: String

) : RequestMoneyInfoPort, RequestMoneyDecreasePort, RequestMoneyIncreasePort, RequestMoneyRechargingPort {

    override fun requestMoneyDecrease(membershipId: MembershipId, amount: Amount): Boolean {
        val responseBody = commonHttpClient.sendPostRequest("${moneyServiceUrl}/decrease/money", "").body()
        return objectMapper.readValue(responseBody, Boolean::class.java)
    }

    override fun requestMoneyIncrease(membershipId: MembershipId, amount: Amount): Boolean {
        return true
    }

    override fun moneyInfo(membershipId: MembershipId): MoneyInfo {
        TODO("Not yet implemented")
    }

    override fun requestRecharging(membershipId: MembershipId, amount: Amount): MoneyInfo {
        val responseBody = commonHttpClient.sendPostRequest("${moneyServiceUrl}/recharge/money", "").body()
        return objectMapper.readValue(responseBody, MoneyInfo::class.java)
    }
}