package com.practice.money.application.port.out

data class Membership(
    var name: String,
    var email: String,
    var address: String,
    var isValid: Boolean,
    var isCorp: Boolean
) {

}
