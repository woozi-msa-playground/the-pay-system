package com.practice.msa.adapter.`in`.web

import com.practice.msa.application.port.`in`.ModifyMembershipCommand

data class ModifyMembershipRequest(
    val name: String,
    val email: String,
    val address: String,
    val isCorp: Boolean,
) {
    fun toCommand(id: Long): ModifyMembershipCommand =
        ModifyMembershipCommand(id, name, email, address, true, isCorp)
}
