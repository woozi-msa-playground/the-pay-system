package com.practice.banking.adapter.`in`.web

import com.practice.banking.application.port.`in`.BankAccountModifyUseCase
import com.practice.common.WebAdapter
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@WebAdapter
@RestController
class ModifyBankAccountApi(
    private val bankAccountModifyUseCase: BankAccountModifyUseCase
) {

    @PutMapping("/bank/modify/{bankAccountId}")
    fun modifyBankAccount(
        @PathVariable bankAccountId: Long,
        @RequestBody modifyBankAccountRequest: ModifyBankAccountRequest
    ): ResponseEntity<ModifyBankAccountResponse> =
        ResponseEntity.ok().body(
            ModifyBankAccountResponse(bankAccountModifyUseCase.modifyBankAccount(modifyBankAccountRequest.toCommand(bankAccountId)))
        )
}