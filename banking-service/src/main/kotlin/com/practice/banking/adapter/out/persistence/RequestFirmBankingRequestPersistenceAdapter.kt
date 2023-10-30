package com.practice.banking.adapter.out.persistence

import com.practice.banking.application.port.out.RequestFirmBankingRequestPort
import com.practice.banking.application.port.out.FirmBankingUpdateStatusPort
import com.practice.banking.domain.FirmBanking
import com.practice.banking.domain.vo.FirmBankingId
import com.practice.banking.domain.vo.FirmBankingStatus
import com.practice.banking.domain.vo.FirmbankingAggregateIdentifier
import com.practice.banking.domain.vo.FromBankAccountNumber
import com.practice.banking.domain.vo.FromBankName
import com.practice.banking.domain.vo.MoneyAmount
import com.practice.banking.domain.vo.ToBankAccountNumber
import com.practice.banking.domain.vo.ToBankName
import com.practice.common.PersistenceAdapter
import jakarta.persistence.EntityNotFoundException
import org.springframework.data.repository.findByIdOrNull
import java.util.UUID

@PersistenceAdapter
class RequestFirmBankingRequestPersistenceAdapter(
    private val springDataFirmBankingRepository: SpringDataFirmBankingRepository
) : RequestFirmBankingRequestPort, FirmBankingUpdateStatusPort {

    override fun createFirmBankingRequest(
        fromBankName: FromBankName,
        fromBankAccountNumber: FromBankAccountNumber,
        toBankName: ToBankName,
        toBankAccountNumber: ToBankAccountNumber,
        moneyAmount: MoneyAmount,
        firmBankingStatus: FirmBankingStatus,
        firmbankingAggregateIdentifier: FirmbankingAggregateIdentifier
    ): FirmBanking =
        springDataFirmBankingRepository.saveAndFlush(
            FirmBankingJpaEntity(
                fromBankName.fromBankName,
                fromBankAccountNumber.fromBankAccountNumber,
                toBankName.toBankName,
                toBankAccountNumber.toBankAccountNumber,
                moneyAmount.moneyAmount,
                firmBankingStatus.firmBankingStatus,
                UUID.randomUUID(),
                firmbankingAggregateIdentifier.firmbankingAggregateIdentifier
            )
        ).let(FirmBankingMapper::mapToEntityDomain)

    override fun updateFirmBankingStatus(
        firmBankingId: FirmBankingId,
        firmBankingStatus: FirmBankingStatus
    ): FirmBanking {
        val entity = springDataFirmBankingRepository.findByIdOrNull(firmBankingId.firmBankingId.toLong())
            ?: throw EntityNotFoundException()
        entity.firmBankingStatus = firmBankingStatus.firmBankingStatus
        return springDataFirmBankingRepository.saveAndFlush(entity)
            .let(FirmBankingMapper::mapToEntityDomain)
    }
}
