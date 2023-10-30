package com.practice.remittance.application.port.out

import com.practice.remittance.domain.RemittanceRequest
import com.practice.remittance.domain.vo.FromMembershipId

fun interface FindRemittancePort {
    fun findRemittanceHistory(fromMembershipId: FromMembershipId): List<RemittanceRequest>
}