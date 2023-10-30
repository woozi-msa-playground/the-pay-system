package com.practice.remittance.adapter.out.persistence

import com.practice.common.InternalSystemAdapter
import com.practice.remittance.application.port.out.CreateRequestRemittancePort
import com.practice.remittance.application.port.out.FindRemittancePort
import com.practice.remittance.application.port.out.UpdateRequestRemittancePort
import com.practice.remittance.domain.RemittanceRequest
import com.practice.remittance.domain.vo.Amount
import com.practice.remittance.domain.vo.FromMembershipId
import com.practice.remittance.domain.vo.RemittanceRequestId
import com.practice.remittance.domain.vo.RemittanceStatus
import com.practice.remittance.domain.vo.RemittanceType
import com.practice.remittance.domain.vo.ToBankAccountNumber
import com.practice.remittance.domain.vo.ToBankName
import com.practice.remittance.domain.vo.ToMembershipId
import jakarta.persistence.EntityNotFoundException
import org.springframework.data.repository.findByIdOrNull
import java.util.UUID

@InternalSystemAdapter
class RequestRemittanceAdapter(
    private val remittanceJpaEntityRepository: RemittanceJpaEntityRepository,
    private val remittanceMapper: RemittanceMapper
): CreateRequestRemittancePort, UpdateRequestRemittancePort, FindRemittancePort {
    override fun createRemittanceRequestHistory(
        fromMembershipId: FromMembershipId,
        toMembershipId: ToMembershipId,
        toBankName: ToBankName,
        toBankAccountNumber: ToBankAccountNumber,
        amount: Amount,
        remittanceType: RemittanceType
    ): RemittanceRequest = remittanceMapper.mapToDomainEntity(
        remittanceJpaEntityRepository.saveAndFlush(
            RemittanceJpaEntity(
                fromMembershipId = fromMembershipId.fromMembershipId,
                toMembershipId = toMembershipId.toMembershipId,
                toBankName = toBankName.toBankName,
                toBankAccountNumber = toBankAccountNumber.toBankAccountNumber,
                amount = amount.amount,
                remittanceType = remittanceType.remittanceType,
                remittanceStatus = "started"
            )
        ),
        UUID.randomUUID()
    )

    override fun updateRemittanceRequestHistory(
        remittanceRequestId: RemittanceRequestId,
        remittanceStatus: RemittanceStatus
    ): RemittanceRequest =
        remittanceJpaEntityRepository.findByIdOrNull(remittanceRequestId.remittanceRequestId.toLong())
            ?.also { it.remittanceStatus = remittanceStatus.remittanceStatus }
            ?.let { remittanceMapper.mapToDomainEntity(it, UUID.randomUUID()) }
            ?: throw EntityNotFoundException()

    override fun findRemittanceHistory(fromMembershipId: FromMembershipId): List<RemittanceRequest> =
        remittanceJpaEntityRepository.findAllByFromMembershipId(fromMembershipId.fromMembershipId)
            .map { remittanceMapper.mapToDomainEntity(it, UUID.randomUUID()) }
}
