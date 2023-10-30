package com.practice.banking.adapter.out.persistence

import com.practice.banking.domain.FirmBanking
import com.practice.banking.domain.vo.FirmBankingId
import com.practice.banking.domain.vo.FirmBankingStatus
import com.practice.banking.domain.vo.FirmbankingAggregateIdentifier
import com.practice.banking.domain.vo.FromBankAccountNumber
import com.practice.banking.domain.vo.FromBankName
import com.practice.banking.domain.vo.MoneyAmount
import com.practice.banking.domain.vo.ToBankAccountNumber
import com.practice.banking.domain.vo.ToBankName
import java.util.UUID

object FirmBankingMapper {

    fun mapToEntityDomain(entity: FirmBankingJpaEntity): FirmBanking {
        return FirmBanking(
            FirmBankingId(entity.firmBankingId.toString()),
            FromBankName(entity.fromBankName),
            FromBankAccountNumber(entity.fromBankAccountNumber),
            ToBankName(entity.toBankName),
            ToBankAccountNumber(entity.toBankAccountNumber),
            MoneyAmount(entity.moneyAmount),
            FirmBankingStatus(entity.firmBankingStatus),
            entity.uuid,
            FirmbankingAggregateIdentifier(entity.firmbankingAggregateIdentifier)
        )
    }
}


