package com.practice.msa.adapter.`in`.web

import com.practice.msa.application.port.`in`.ModifyMembershipUseCase
import com.pratice.common.WebAdapter
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@WebAdapter
@RestController
class ModifyMembershipApi(
    private val modifyMembershipUseCase: ModifyMembershipUseCase
) {

    @PutMapping("/membership/modify/{membershipId}")
    fun modifyMembership(
        @PathVariable membershipId: Long,
        @RequestBody modifyMembershipRequest: ModifyMembershipRequest
    ): ResponseEntity<ModifyMembershipResponse> =
        ResponseEntity.ok().body(
            ModifyMembershipResponse(
                modifyMembershipUseCase.modifyMembership(
                    modifyMembershipRequest.toCommand(
                        membershipId
                    )
                )
            )
        )
}