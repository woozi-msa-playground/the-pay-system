package com.practice.banking.domain

import com.practice.banking.domain.vo.FirmBankingId
import com.practice.banking.domain.vo.FirmBankingStatus
import com.practice.banking.domain.vo.FirmbankingAggregateIdentifier
import com.practice.banking.domain.vo.FromBankAccountNumber
import com.practice.banking.domain.vo.FromBankName
import com.practice.banking.domain.vo.MoneyAmount
import com.practice.banking.domain.vo.ToBankAccountNumber
import com.practice.banking.domain.vo.ToBankName
import java.util.UUID

data class FirmBanking(
    val firmBankingId: String,
    val fromBankName: String,
    val fromBankAccountNumber: String,
    val toBankName: String,
    val toBankAccountNumber: String,
    val moneyAmount: Int,
    val firmBankingStatus: Int,
    val uuid: String,
    val firmbankingAggregateIdentifier: String,
) {
    fun updateStatus(firmBankingStatus: Int): FirmBanking =
        FirmBanking(
            this.firmBankingId,
            this.fromBankName,
            this.fromBankAccountNumber,
            this.toBankName,
            this.toBankAccountNumber,
            this.moneyAmount,
            firmBankingStatus,
            this.uuid,
            this.firmbankingAggregateIdentifier
        )

    constructor(
        firmBankingId: FirmBankingId,
        fromBankName: FromBankName,
        fromBankAccountNumber: FromBankAccountNumber,
        toBankName: ToBankName,
        toBankAccountNumber: ToBankAccountNumber,
        moneyAmount: MoneyAmount,
        firmBankingStatus: FirmBankingStatus,
        uuid: UUID,
        firmbankingAggregateIdentifier: FirmbankingAggregateIdentifier
    ) : this(
        firmBankingId.firmBankingId,
        fromBankName.fromBankName,
        fromBankAccountNumber.fromBankAccountNumber,
        toBankName.toBankName,
        toBankAccountNumber.toBankAccountNumber,
        moneyAmount.moneyAmount,
        firmBankingStatus.firmBankingStatus,
        uuid.toString(),
        firmbankingAggregateIdentifier.firmbankingAggregateIdentifier
    )
}
