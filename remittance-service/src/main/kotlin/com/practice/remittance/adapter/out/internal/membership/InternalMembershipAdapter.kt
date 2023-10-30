package com.practice.remittance.adapter.out.internal.membership

import com.fasterxml.jackson.databind.ObjectMapper
import com.practice.common.CommonHttpClient
import com.practice.common.InternalSystemAdapter
import com.practice.remittance.application.port.out.membership.CheckMembershipIdPort
import com.practice.remittance.application.port.out.membership.MembershipStatus
import com.practice.remittance.application.port.out.membership.MembershipStatusPort
import com.practice.remittance.domain.vo.MembershipId
import org.springframework.beans.factory.annotation.Value

@InternalSystemAdapter
class InternalMembershipAdapter(
    private val commonHttpClient: CommonHttpClient,
    private val objectMapper: ObjectMapper,
    @Value("\${service.membership.url}") private val membershipServiceUrl: String
) : CheckMembershipIdPort, MembershipStatusPort {

    override fun checkMembershipId(membershipId: Long): Boolean = true

    override fun membershipStatus(membershipId: MembershipId): MembershipStatus {
        val requestBody = commonHttpClient.sendGetRequest("$membershipServiceUrl/membership/${membershipId.membershipId}").body()
        val membership = objectMapper.readValue(requestBody, Membership::class.java)
        if(membership.isValid){
            return MembershipStatus(membershipId.membershipId, true)
        }
        return MembershipStatus(membershipId.membershipId, false)
    }
}