package com.practice.msa.domain

import com.practice.msa.domain.vo.MembershipId
import com.practice.msa.domain.vo.MembershipAddress
import com.practice.msa.domain.vo.MembershipEmail
import com.practice.msa.domain.vo.MembershipIsCorp
import com.practice.msa.domain.vo.MembershipIsValid
import com.practice.msa.domain.vo.MembershipName

data class Membership(
    val memberShipId: String,
    val name: String,
    val email: String,
    val address: String,
    val isValid: Boolean,
    val isCorp: Boolean,
) {
    constructor(
        memberShipId: MembershipId,
        name: MembershipName,
        email: MembershipEmail,
        address: MembershipAddress,
        isValid: MembershipIsValid,
        isCorp: MembershipIsCorp,
    ) : this(
        memberShipId.membershipId,
        name.name,
        email.email,
        address.address,
        isValid.isValid,
        isCorp.isCorp
    )
}
