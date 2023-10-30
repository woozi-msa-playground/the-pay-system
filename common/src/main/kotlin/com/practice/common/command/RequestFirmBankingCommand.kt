package com.practice.common.command

import com.fasterxml.jackson.annotation.JsonCreator
import org.axonframework.modelling.command.TargetAggregateIdentifier

data class RequestFirmBankingCommand @JsonCreator constructor(
    val requestFirmBankingId: String,
    @field:TargetAggregateIdentifier
    val aggregateIdentifier: String,
    val rechargeRequestId: String,
    val membershipId: String,
    val checkRegisteredBankAccountId: String,
    val toBankName: String,
    val toBankAccountNumber: String,
    val fromBankName: String,
    val fromBankAccountNumber: String,
    val amount: Int
)