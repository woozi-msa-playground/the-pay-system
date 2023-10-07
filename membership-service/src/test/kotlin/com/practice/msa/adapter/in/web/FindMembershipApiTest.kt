package com.practice.msa.adapter.`in`.web

import com.fasterxml.jackson.databind.ObjectMapper
import com.practice.msa.domain.Membership
import com.practice.msa.domain.vo.MembershipAddress
import com.practice.msa.domain.vo.MembershipEmail
import com.practice.msa.domain.vo.MembershipId
import com.practice.msa.domain.vo.MembershipIsCorp
import com.practice.msa.domain.vo.MembershipIsValid
import com.practice.msa.domain.vo.MembershipName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@SpringBootTest
class FindMembershipApiTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Test
    fun test() {
        val request = RegisterMembershipRequest("tester", "tester@test.com", "Korea", true)
        val expected = Membership(
            MembershipId("1"),
            MembershipName("tester"),
            MembershipEmail("tester@test.com"),
            MembershipAddress("Korea"),
            MembershipIsValid(true),
            MembershipIsCorp(true)
        )

        mockMvc.perform(
            MockMvcRequestBuilders.post("/membership/register")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(objectMapper.writeValueAsString(request))
        ).andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(expected)))
    }
}