package com.practice.money.adapter.out.internal

import com.fasterxml.jackson.databind.ObjectMapper
import com.practice.common.CommonHttpClient
import com.practice.common.InternalSystemAdapter
import com.practice.money.application.port.out.FindRegisteredBankAccountPort
import com.practice.money.application.port.out.MembershipStatus
import com.practice.money.application.port.out.RechargingMoneyPort
import com.practice.money.application.port.out.RegisteredBankAccountAggregate
import com.practice.money.domain.vo.MembershipId
import com.practice.money.domain.vo.MoneyBalance
import org.springframework.beans.factory.annotation.Value

@InternalSystemAdapter
class InternalBankingAdapter(
    private val commonHttpClient: CommonHttpClient,
    private val objectMapper: ObjectMapper,
    @Value("\${service.banking.url}") private val bakingServiceUrl: String
) : FindRegisteredBankAccountPort {

    override fun findRegisteredBankAccountAggregateIdentifier(membershipId: MembershipId): RegisteredBankAccountAggregate {
        val requestBody = commonHttpClient.sendGetRequest("$bakingServiceUrl/banking/account/${membershipId.membershipId.toLong()}").body()
        val registeredBankAccount = objectMapper.readValue(requestBody, RegisteredBankAccount::class.java)
        return RegisteredBankAccountAggregate(
            registeredBankAccount.bankAccountAggregateIdentifier,
            registeredBankAccount.bankAccountId,
            registeredBankAccount.membershipId,
            registeredBankAccount.bankName,
            registeredBankAccount.bankAccountNumber
        )
    }
}