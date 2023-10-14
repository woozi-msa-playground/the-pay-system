package com.practice.msa.adapter.`in`.web

import com.practice.msa.application.port.`in`.RegisterMembershipUseCase
import com.practice.common.WebAdapter
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@WebAdapter
@RestController
class RegisterMembershipApi(
    private val registerMembershipUseCase: RegisterMembershipUseCase
) {

    @PostMapping("/membership/register")
    fun registerMembership(@RequestBody registerMembershipRequest: RegisterMembershipRequest): ResponseEntity<RegisterMembershipResponse> =
        ResponseEntity.ok().body(
            RegisterMembershipResponse(registerMembershipUseCase.registerMembership(registerMembershipRequest.toCommand()))
        )
}