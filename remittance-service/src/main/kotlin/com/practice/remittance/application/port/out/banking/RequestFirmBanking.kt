package com.practice.remittance.application.port.out.banking

import com.practice.remittance.domain.vo.Amount
import com.practice.remittance.domain.vo.FromMembershipId
import com.practice.remittance.domain.vo.ToBankAccountNumber
import com.practice.remittance.domain.vo.ToBankName

fun interface RequestFirmBanking {
    fun requestFirmBanking(
        fromMembershipId: FromMembershipId,
        toBankName: ToBankName,
        toBankAccountNumber: ToBankAccountNumber,
        amount: Amount
    ): Boolean
}