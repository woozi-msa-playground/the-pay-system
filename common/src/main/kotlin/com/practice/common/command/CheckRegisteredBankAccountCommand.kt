package com.practice.common.command

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import org.axonframework.modelling.command.TargetAggregateIdentifier

data class CheckRegisteredBankAccountCommand(
    val checkRegisteredBankAccountId: String,

    @TargetAggregateIdentifier
    val aggregateIdentifier: String,
    val membershipId: String,
    val rechargeRequestId: String,
    val bankName: String,
    val bankAccountNumber: String,
    val amount: Int
)