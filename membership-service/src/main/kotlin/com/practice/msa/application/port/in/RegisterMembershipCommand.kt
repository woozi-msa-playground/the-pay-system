package com.practice.msa.application.port.`in`

import com.practice.msa.common.SelfValidating
import jakarta.validation.constraints.AssertTrue
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class RegisterMembershipCommand(

    @field:NotNull
    val name: String,

    @field:NotNull
    val email: String,

    @field:NotNull
    @field:NotBlank
    val address: String,

    @field:AssertTrue
    val isValid: Boolean,

    val isCorp: Boolean,

    ) : SelfValidating<RegisterMembershipCommand>() {
    init {
        this.validateSelf()
    }
}