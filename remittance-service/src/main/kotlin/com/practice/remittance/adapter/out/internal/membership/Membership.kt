package com.practice.remittance.adapter.out.internal.membership

data class Membership(
    var name: String,
    var email: String,
    var address: String,
    var isValid: Boolean,
    var isCorp: Boolean
) {

}