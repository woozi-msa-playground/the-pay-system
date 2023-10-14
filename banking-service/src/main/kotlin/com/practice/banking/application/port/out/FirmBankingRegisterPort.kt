package com.practice.banking.application.port.out

import com.practice.banking.domain.FirmBanking
import com.practice.banking.domain.vo.FirmBankingStatus
import com.practice.banking.domain.vo.FromBankAccountNumber
import com.practice.banking.domain.vo.FromBankName
import com.practice.banking.domain.vo.MoneyAmount
import com.practice.banking.domain.vo.ToBankAccountNumber
import com.practice.banking.domain.vo.ToBankName

fun interface FirmBankingRegisterPort {
    fun createFirmBanking(
        fromBankName: FromBankName,
        fromBankAccountNumber: FromBankAccountNumber,
        toBankName: ToBankName,
        toBankAccountNumber: ToBankAccountNumber,
        moneyAmount: MoneyAmount,
        firmBankingStatus: FirmBankingStatus,
    ): FirmBanking
}