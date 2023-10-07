package com.practice.msa.application.port.`in`

import com.pratice.common.SelfValidating
import jakarta.validation.constraints.AssertTrue
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class ModifyMembershipCommand(

    @field:Min(1)
    @field:NotNull
    val id: Long,

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

    ) : SelfValidating<ModifyMembershipCommand>() {
    init {
        this.validateSelf()
    }
}