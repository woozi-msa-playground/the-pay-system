package com.practice.banking.application.port.`in`

import com.pratice.common.SelfValidating
import jakarta.validation.constraints.AssertTrue
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class BankAccountRegisterCommand(

    @field:Min(1)
    @field:NotNull
    val membershipId: Long,

    @field:NotNull
    val bankName: String,

    @field:NotNull
    @field:NotBlank
    val bankAccount: String,

    @field:AssertTrue
    val linkedStatusIsValid: Boolean,

    ) : SelfValidating<BankAccountRegisterCommand>() {
    init {
        this.validateSelf()
    }
}