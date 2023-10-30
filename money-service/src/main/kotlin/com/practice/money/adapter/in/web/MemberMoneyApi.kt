package com.practice.money.adapter.`in`.web

import com.practice.money.application.port.`in`.IncreaseMemberMoneyUseCase
import com.practice.common.WebAdapter
import com.practice.money.adapter.`in`.web.dto.CreateMemberMoneyRequest
import com.practice.money.adapter.`in`.web.dto.DecreaseMemberMoneyRequest
import com.practice.money.adapter.`in`.web.dto.DecreaseMemberMoneyResponse
import com.practice.money.adapter.`in`.web.dto.IncreaseMemberMoneyRequest
import com.practice.money.adapter.`in`.web.dto.IncreaseMemberMoneyResponse
import com.practice.money.adapter.`in`.web.dto.IncreaseMemberMoneyWithAxonResponse
import com.practice.money.application.port.`in`.AxonIncreaseMemberMoneyUseCase
import com.practice.money.application.port.`in`.CreateMemberMoneyUseCase
import com.practice.money.application.port.`in`.DecreaseMemberMoneyUseCase
import com.practice.money.application.port.`in`.IncreaseMemberMoneyAsyncUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@WebAdapter
@RestController
class MemberMoneyApi(
    private val createMemberMoneyUseCase: CreateMemberMoneyUseCase,
    private val increaseMemberMoneyUseCase: IncreaseMemberMoneyUseCase,
    private val axonIncreaseMemberMoneyUseCase: AxonIncreaseMemberMoneyUseCase,
    private val decreaseMemberMoneyUseCase: DecreaseMemberMoneyUseCase,
    // private val rechargeMemberMoneyUseCase: RechargeMemberMoneyUseCase,
    private val increaseMemberMoneyAsyncUseCase: IncreaseMemberMoneyAsyncUseCase
) {

    @PostMapping("/money/create")
    fun createMoney(@RequestBody createMemberMoneyRequest: CreateMemberMoneyRequest): ResponseEntity<Boolean> =
        ResponseEntity.ok().body(
            createMemberMoneyUseCase.createMemberMoney(createMemberMoneyRequest.toCommand())
        )

    @PostMapping("/money/increase/axon")
    fun increaseMoneyWitAxon(@RequestBody increaseMemberMoneyRequest: IncreaseMemberMoneyRequest): ResponseEntity<IncreaseMemberMoneyWithAxonResponse> =
        ResponseEntity.ok().body(
            IncreaseMemberMoneyWithAxonResponse(axonIncreaseMemberMoneyUseCase.rechargingMoney(increaseMemberMoneyRequest.toCommand()))
        )

    @PostMapping("/money/increase")
    fun increaseMoney(@RequestBody increaseMemberMoneyRequest: IncreaseMemberMoneyRequest): ResponseEntity<IncreaseMemberMoneyResponse> =
        ResponseEntity.ok().body(
            IncreaseMemberMoneyResponse(increaseMemberMoneyUseCase.increaseMoneyRequest(increaseMemberMoneyRequest.toCommand()))
        )

    @PostMapping("/money/decrease")
    fun decreaseMoney(@RequestBody decreaseMemberMoneyRequest: DecreaseMemberMoneyRequest): ResponseEntity<DecreaseMemberMoneyResponse> =
        ResponseEntity.ok().body(
            DecreaseMemberMoneyResponse(decreaseMemberMoneyUseCase.decreaseMoneyRequest(decreaseMemberMoneyRequest.toCommand()))
        )

    // @PostMapping("/money/recharge")
    // fun rechargeMoney(@RequestBody rechargeMemberMoneyRequest: RechargeMemberMoneyRequest): ResponseEntity<RechargeMemberMoneyResponse> =
    //     rechargeMemberMoneyUseCase.rechargeMoneyRequest(rechargeMemberMoneyRequest.toCommand())
    //     ResponseEntity.ok().body()
    //

    @PostMapping("/money/increase/async")
    fun changeMoney(@RequestBody increaseMemberMoneyRequest: IncreaseMemberMoneyRequest): ResponseEntity<IncreaseMemberMoneyResponse> =
        ResponseEntity.ok().body(
            IncreaseMemberMoneyResponse(increaseMemberMoneyAsyncUseCase.increaseMoneyRequestAsync(increaseMemberMoneyRequest.toCommand()))
        )
}