package com.practice.remittance.adapter.out.persistence

import com.practice.remittance.domain.RemittanceRequest
import com.practice.remittance.domain.vo.Amount
import com.practice.remittance.domain.vo.FromMembershipId
import com.practice.remittance.domain.vo.RemittanceRequestId
import com.practice.remittance.domain.vo.RemittanceStatus
import com.practice.remittance.domain.vo.RemittanceType
import com.practice.remittance.domain.vo.ToBankAccountNumber
import com.practice.remittance.domain.vo.ToBankName
import com.practice.remittance.domain.vo.ToMembershipId
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class RemittanceMapper(
) {
    fun mapToDomainEntity(remittanceJpaEntity: RemittanceJpaEntity, uuid: UUID): RemittanceRequest {
        return RemittanceRequest(
            RemittanceRequestId(remittanceJpaEntity.remittanceRequestId.toString()),
            FromMembershipId(remittanceJpaEntity.fromMembershipId),
            ToMembershipId(remittanceJpaEntity.toMembershipId),
            ToBankName(remittanceJpaEntity.toBankName),
            ToBankAccountNumber(remittanceJpaEntity.toBankAccountNumber),
            RemittanceType(remittanceJpaEntity.remittanceType),
            Amount(remittanceJpaEntity.amount),
            RemittanceStatus(remittanceJpaEntity.remittanceStatus)
        )
    }
}