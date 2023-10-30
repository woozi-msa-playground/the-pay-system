package com.practice.remittance.application

import com.practice.remittance.application.port.`in`.RemittanceRequestCommand
import com.practice.remittance.domain.RemittanceRequest

fun interface RequestRemittanceUseCase {
    fun requestRemittance(command: RemittanceRequestCommand) : RemittanceRequest
}