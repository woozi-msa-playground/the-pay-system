package com.practice.remittance.adapter.`in`.web

import com.practice.common.SelfValidating
import jakarta.validation.constraints.NotNull

data class FindRemittanceCommand(
    @NotNull val membershipId: String
) : SelfValidating<FindRemittanceCommand>() {
    init {
        validateSelf()
    }
}