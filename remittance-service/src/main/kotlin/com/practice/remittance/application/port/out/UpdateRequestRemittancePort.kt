package com.practice.remittance.application.port.out

import com.practice.remittance.domain.RemittanceRequest
import com.practice.remittance.domain.vo.RemittanceRequestId
import com.practice.remittance.domain.vo.RemittanceStatus
import com.practice.remittance.domain.vo.RemittanceType

fun interface UpdateRequestRemittancePort {
    fun updateRemittanceRequestHistory(
        remittanceRequestId: RemittanceRequestId,
        remittanceStatus: RemittanceStatus
    ): RemittanceRequest
}