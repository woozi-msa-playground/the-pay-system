package com.practice.banking.domain

import com.practice.banking.domain.vo.FirmBankingId
import com.practice.banking.domain.vo.FirmBankingStatus
import com.practice.banking.domain.vo.FromBankAccountNumber
import com.practice.banking.domain.vo.FromBankName
import com.practice.banking.domain.vo.MoneyAmount
import com.practice.banking.domain.vo.ToBankAccountNumber
import com.practice.banking.domain.vo.ToBankName

data class FirmBanking(
    val firmBankingId: String,
    val fromBankName: String,
    val fromBankAccountNumber: String,
    val toBankName: String,
    val toBankAccountNumber: String,
    val moneyAmount: Int,
    val firmBankingStatus: Int,
) {
    fun updateStatus(firmBankingStatus: Int): FirmBanking =
        FirmBanking(
            this.firmBankingId,
            this.fromBankName,
            this.fromBankAccountNumber,
            this.toBankName,
            this.toBankAccountNumber,
            this.moneyAmount,
            firmBankingStatus
        )

    constructor(
        firmBankingId: FirmBankingId,
        fromBankName: FromBankName,
        fromBankAccountNumber: FromBankAccountNumber,
        toBankName: ToBankName,
        toBankAccountNumber: ToBankAccountNumber,
        moneyAmount: MoneyAmount,
        firmBankingStatus: FirmBankingStatus
    ) : this(
        firmBankingId.firmBankingId,
        fromBankName.fromBankName,
        fromBankAccountNumber.fromBankAccountNumber,
        toBankName.toBankName,
        toBankAccountNumber.toBankAccountNumber,
        moneyAmount.moneyAmount,
        firmBankingStatus.firmBankingStatus,
    )
}
