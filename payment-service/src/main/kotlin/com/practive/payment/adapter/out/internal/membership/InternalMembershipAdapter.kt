package com.practive.payment.adapter.out.internal.membership

import com.fasterxml.jackson.databind.ObjectMapper
import com.practice.banking.application.port.out.MembershipStatusPort
import com.practice.banking.application.port.out.Membership
import com.practice.banking.application.port.out.MembershipStatus
import com.practice.banking.application.port.out.RequestVerifyMembershipIdPort
import com.practice.banking.domain.vo.MembershipId
import com.practice.common.CommonHttpClient
import com.practice.common.InternalSystemAdapter
import org.springframework.beans.factory.annotation.Value

@InternalSystemAdapter
class InternalMembershipAdapter(
    private val commonHttpClient: CommonHttpClient,
    private val objectMapper: ObjectMapper,
    @Value("\${service.membership.url}") private val membershipServiceUrl: String
) : RequestVerifyMembershipIdPort, MembershipStatusPort {
    override fun checkMembershipId(membershipId: MembershipId): Boolean = true

    override fun membershipStatus(membershipId: MembershipId): MembershipStatus {
        val requestBody = commonHttpClient.sendGetRequest("$membershipServiceUrl/membership/${membershipId.membershipId}").body()
        val membership = objectMapper.readValue(requestBody, Membership::class.java)
        if(membership.isValid){
            return MembershipStatus(membershipId.membershipId.toString(), true)
        }
        return MembershipStatus(membershipId.membershipId.toString(), false)
    }
}