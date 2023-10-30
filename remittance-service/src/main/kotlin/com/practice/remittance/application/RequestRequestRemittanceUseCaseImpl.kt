package com.practice.remittance.application

import com.practice.remittance.application.port.`in`.RemittanceRequestCommand
import com.practice.remittance.application.port.out.CreateRequestRemittancePort
import com.practice.remittance.application.port.out.UpdateRequestRemittancePort
import com.practice.remittance.application.port.out.banking.RequestFirmBanking
import com.practice.remittance.application.port.out.membership.MembershipStatusPort
import com.practice.remittance.application.port.out.money.RequestMoneyDecreasePort
import com.practice.remittance.application.port.out.money.RequestMoneyIncreasePort
import com.practice.remittance.application.port.out.money.RequestMoneyInfoPort
import com.practice.remittance.application.port.out.money.RequestMoneyRechargingPort
import com.practice.remittance.domain.RemittanceRequest
import com.practice.remittance.domain.vo.Amount
import com.practice.remittance.domain.vo.FromMembershipId
import com.practice.remittance.domain.vo.MembershipId
import com.practice.remittance.domain.vo.RemittanceRequestId
import com.practice.remittance.domain.vo.RemittanceStatus
import com.practice.remittance.domain.vo.RemittanceType
import com.practice.remittance.domain.vo.ToBankAccountNumber
import com.practice.remittance.domain.vo.ToBankName
import com.practice.remittance.domain.vo.ToMembershipId
import org.springframework.stereotype.Service
import kotlin.math.ceil

@Service
class RequestRequestRemittanceUseCaseImpl(
    private val createRequestRemittancePort: CreateRequestRemittancePort,
    private val updateRequestRemittancePort: UpdateRequestRemittancePort,
    private val membershipStatusPort: MembershipStatusPort,
    private val requestMoneyIncreasePort: RequestMoneyIncreasePort,
    private val requestMoneyDecreasePort: RequestMoneyDecreasePort,
    private val requestMoneyRechargingPort: RequestMoneyRechargingPort,
    private val requestMoneyInfoPort: RequestMoneyInfoPort,
    private val requestFirmBanking: RequestFirmBanking,
): RequestRemittanceUseCase {

    override fun requestRemittance(command: RemittanceRequestCommand) :RemittanceRequest {
        val remittanceRequest = createRequestRemittancePort.createRemittanceRequestHistory(
            FromMembershipId(command.fromMembershipId),
            ToMembershipId(command.toMembershipId),
            ToBankName(command.toBankName),
            ToBankAccountNumber(command.toBankAccountNumber),
            Amount(command.amount),
            RemittanceType(command.remittanceType)
        )
        val membershipStatus = membershipStatusPort.membershipStatus(MembershipId(command.fromMembershipId))
        if(!membershipStatus.isValid) {
            throw RuntimeException()
        }
        val moneyInfo = requestMoneyInfoPort.moneyInfo(MembershipId(command.fromMembershipId))
        if(moneyInfo.balance < command.amount) {
            val amount = Amount((ceil((command.amount - moneyInfo.balance) / 10000.0) * 10000).toInt())
            requestMoneyRechargingPort.requestRecharging(MembershipId(command.fromMembershipId), amount)
        }
        if(command.remittanceType == 0) {
            val decreaseResult = requestMoneyDecreasePort.requestMoneyDecrease(
                MembershipId(command.fromMembershipId),
                Amount(command.amount)
            )
            val increaseResult = requestMoneyIncreasePort.requestMoneyIncrease(
                MembershipId(command.toMembershipId),
                Amount(command.amount)
            )
            if(!decreaseResult || !increaseResult) {
                throw RuntimeException()
            }
        }
         else if(command.remittanceType == 1) {
            val decreaseResult = requestMoneyDecreasePort.requestMoneyDecrease(
                MembershipId(command.fromMembershipId),
                Amount(command.amount)
            )
            val firmBankingResult =
                requestFirmBanking.requestFirmBanking(
                    FromMembershipId(command.fromMembershipId),
                    ToBankName(command.toBankName),
                    ToBankAccountNumber(command.toBankAccountNumber),
                    Amount(command.amount)
                )
            if(!decreaseResult || !firmBankingResult) {
                throw RuntimeException()
            }
        }
        remittanceRequest.remittanceStatus = "success"
        return updateRequestRemittancePort.updateRemittanceRequestHistory(
            RemittanceRequestId(remittanceRequest.remittanceRequestId),
            RemittanceStatus(remittanceRequest.remittanceStatus)
        )
    }
}