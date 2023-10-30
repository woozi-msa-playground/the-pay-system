package com.practice.remittance.application

import com.practice.remittance.adapter.`in`.web.FindRemittanceCommand
import com.practice.remittance.domain.RemittanceRequest

interface FindRemittanceUseCase {
    fun findRemittanceByMembershipId(membershipId: FindRemittanceCommand): List<RemittanceRequest>
}