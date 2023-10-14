package com.practice.banking.adapter.out.persistence

import com.practice.banking.application.port.out.FirmBankingRegisterPort
import com.practice.banking.application.port.out.FirmBankingUpdateStatusPort
import com.practice.banking.domain.FirmBanking
import com.practice.banking.domain.vo.FirmBankingId
import com.practice.banking.domain.vo.FirmBankingStatus
import com.practice.banking.domain.vo.FromBankAccountNumber
import com.practice.banking.domain.vo.FromBankName
import com.practice.banking.domain.vo.MoneyAmount
import com.practice.banking.domain.vo.ToBankAccountNumber
import com.practice.banking.domain.vo.ToBankName
import com.pratice.common.PersistenceAdapter
import java.util.UUID

@PersistenceAdapter
class FirmBankingRegisterPersistenceAdapter(
    private val springDataFirmBankingRepository: SpringDataFirmBankingRepository
) : FirmBankingRegisterPort, FirmBankingUpdateStatusPort {

    override fun createFirmBanking(
        fromBankName: FromBankName,
        fromBankAccountNumber: FromBankAccountNumber,
        toBankName: ToBankName,
        toBankAccountNumber: ToBankAccountNumber,
        moneyAmount: MoneyAmount,
        firmBankingStatus: FirmBankingStatus
    ): FirmBanking = FirmBankingMapper.mapToEntityDomain(
        springDataFirmBankingRepository.saveAndFlush(
            FirmBankingJpaEntity(
                fromBankName.fromBankName,
                fromBankAccountNumber.fromBankAccountNumber,
                toBankName.toBankName,
                toBankAccountNumber.toBankAccountNumber,
                moneyAmount.moneyAmount,
                firmBankingStatus.firmBankingStatus,
                UUID.randomUUID()
            )
        )
    )

    override fun updateFirmBankingStatus(
        firmBankingId: FirmBankingId,
        firmBankingStatus: FirmBankingStatus
    ): FirmBanking {
        TODO("Not yet implemented")
    }
}
