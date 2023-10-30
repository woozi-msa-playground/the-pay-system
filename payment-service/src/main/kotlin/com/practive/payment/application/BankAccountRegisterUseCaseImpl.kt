package com.practive.payment.application

import com.practice.common.UseCase
import com.practive.payment.application.port.`in`.RequestPaymentCommand
import com.practive.payment.application.port.`in`.RequestPaymentUsecase

@UseCase
class BankAccountRegisterUseCaseImpl(
) : RequestPaymentUsecase {

    override fun requestPayment(command: RequestPaymentCommand) {

    }
}
