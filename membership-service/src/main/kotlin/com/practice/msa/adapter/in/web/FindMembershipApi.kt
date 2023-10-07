package com.practice.msa.adapter.`in`.web

import com.practice.msa.application.port.`in`.FindMembershipUseCase
import com.pratice.common.WebAdapter
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@WebAdapter
@RestController
class FindMembershipApi(
    private val findMembershipUseCase: FindMembershipUseCase
) {

    @GetMapping("/membership/{membershipId}")
    fun findMembership(@PathVariable membershipId: Long): ResponseEntity<FindMembershipResponse> =
        ResponseEntity.ok().body(
            FindMembershipResponse(findMembershipUseCase.findMembership(membershipId))
        )
}