package com.practice.money.adapter.out.internal

import com.fasterxml.jackson.databind.ObjectMapper
import com.practice.common.CommonHttpClient
import com.practice.common.InternalSystemAdapter
import com.practice.money.application.port.out.Membership
import com.practice.money.application.port.out.MembershipStatus
import com.practice.money.application.port.out.MembershipStatusPort
import com.practice.money.domain.vo.MembershipId
import org.springframework.beans.factory.annotation.Value

@InternalSystemAdapter
class InternalMembershipAdapter(
    private val commonHttpClient: CommonHttpClient,
    private val objectMapper: ObjectMapper,
    @Value("\${service.membership.url}") private val membershipServiceUrl: String
) : MembershipStatusPort {

    override fun membershipStatus(membershipId: MembershipId): MembershipStatus {
        val requestBody =
            commonHttpClient.sendGetRequest("$membershipServiceUrl/membership/${membershipId.membershipId}").body()
        val membership = objectMapper.readValue(requestBody, Membership::class.java)
        if (membership.isValid) {
            return MembershipStatus(membershipId.membershipId, true)
        }
        return MembershipStatus(membershipId.membershipId, false)
    }
}