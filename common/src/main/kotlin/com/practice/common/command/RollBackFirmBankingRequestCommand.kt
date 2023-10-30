package com.practice.common.command

import com.fasterxml.jackson.annotation.JsonCreator
import org.axonframework.modelling.command.TargetAggregateIdentifier

data class RollBackFirmBankingRequestCommand @JsonCreator constructor(
    val rollbackFirmBankingId: String,
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