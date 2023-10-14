package com.practice.money.adapter.`in`.web

import com.practice.money.application.port.`in`.IncreaseMemberMoneyUseCase
import com.pratice.common.WebAdapter
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@WebAdapter
@RestController
class MemberMoneyApi(
    private val increaseMemberMoneyUseCase: IncreaseMemberMoneyUseCase
) {

    @PostMapping("/money/increase")
    fun changeMoney(@RequestBody increaseMemberMoneyRequest: IncreaseMemberMoneyRequest): ResponseEntity<IncreaseMemberMoneyResponse> =
        ResponseEntity.ok().body(
            IncreaseMemberMoneyResponse(increaseMemberMoneyUseCase.increaseMemberMoneyBalance(increaseMemberMoneyRequest.toCommand()))
        )
}