package com.practice.remittance.application

import com.practice.remittance.adapter.`in`.web.FindRemittanceCommand
import com.practice.remittance.application.port.out.FindRemittancePort
import com.practice.remittance.domain.RemittanceRequest
import com.practice.remittance.domain.vo.FromMembershipId
import org.springframework.stereotype.Service

@Service
class FindRequestRemittanceUseCaseImpl(
    private val findRemittancePort: FindRemittancePort,
): FindRemittanceUseCase {

    override fun findRemittanceByMembershipId(command: FindRemittanceCommand): List<RemittanceRequest> =
        findRemittancePort.findRemittanceHistory(FromMembershipId(command.membershipId))
}