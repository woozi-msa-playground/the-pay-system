package com.practice.msa.adapter.`in`.web

import com.practice.msa.application.port.`in`.RegisterMembershipCommand

data class RegisterMembershipRequest(
    val name: String,
    val email: String,
    val address: String,
    val isCorp: Boolean,
) {
    fun toCommand(): RegisterMembershipCommand =
        RegisterMembershipCommand(name, email, address, true, isCorp)
}
