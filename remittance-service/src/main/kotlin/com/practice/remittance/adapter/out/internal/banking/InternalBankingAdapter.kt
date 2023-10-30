package com.practice.remittance.adapter.out.internal.banking

import com.fasterxml.jackson.databind.ObjectMapper
import com.practice.common.CommonHttpClient
import com.practice.common.InternalSystemAdapter
import com.practice.remittance.application.port.out.banking.FirmBankingInfo
import com.practice.remittance.application.port.out.banking.RequestBankingInfo
import com.practice.remittance.application.port.out.banking.RequestFirmBanking
import com.practice.remittance.domain.vo.Amount
import com.practice.remittance.domain.vo.FromMembershipId
import com.practice.remittance.domain.vo.ToBankAccountNumber
import com.practice.remittance.domain.vo.ToBankName
import org.springframework.beans.factory.annotation.Value

@InternalSystemAdapter
class InternalBankingAdapter(
    private val commonHttpClient: CommonHttpClient,
    private val objectMapper: ObjectMapper,
    @Value("\${service.banking.url}") private val bankingServiceUrl: String

) : RequestBankingInfo, RequestFirmBanking {

    override fun requestBankInfo(bankName: String, bankAccountNumber: String): FirmBankingInfo {
        val requestBody = commonHttpClient.sendGetRequest("$bankingServiceUrl/bank/{bankAccountId}").body()
        return objectMapper.readValue(requestBody, FirmBankingInfo::class.java)
    }

    override fun requestFirmBanking(
        fromMembershipId: FromMembershipId,
        toBankName: ToBankName,
        toBankAccountNumber: ToBankAccountNumber,
        amount: Amount
    ): Boolean {
        val firmBankingRequest = FirmBankingRequest(
            fromMembershipId.fromMembershipId,
            toBankName.toBankName,
            toBankAccountNumber.toBankAccountNumber,
            amount.amount
        )
        val jsonBody = objectMapper.writeValueAsString(firmBankingRequest)
        val requestBody =
            commonHttpClient.sendPostRequest("$bankingServiceUrl/bank/firmbanking/transfer", jsonBody).body()
        val firmBankingInfo = objectMapper.readValue(requestBody, FirmBankingInfo::class.java)
        return firmBankingInfo != null
    }
}

data class FirmBankingRequest(
    val fromMembershipId: String,
    val toBankName: String,
    val toBankAccountNumber: String,
    val amount: Int
)
