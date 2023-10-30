package com.practice.banking.adapter.out.persistence

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.UUID

@Table(name = "firm_banking")
@Entity
class FirmBankingJpaEntity(
    var fromBankName: String,
    var fromBankAccountNumber: String,
    var toBankName: String,
    var toBankAccountNumber: String,
    var moneyAmount: Int,
    var firmBankingStatus: Int,
    var uuid: UUID,
    var firmbankingAggregateIdentifier: String,

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var firmBankingId: Long? = null
)