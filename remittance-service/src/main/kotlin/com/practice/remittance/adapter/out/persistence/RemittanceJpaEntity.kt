package com.practice.remittance.adapter.out.persistence

import com.practice.remittance.domain.vo.ToMembershipId
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class RemittanceJpaEntity(

    var fromMembershipId: String,
    var toMembershipId: String,
    var toBankName: String,
    var toBankAccountNumber: String,
    var remittanceType: Int,
    var remittanceStatus: String,
    var amount: Int,

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var remittanceRequestId: Long? = null
) {
}