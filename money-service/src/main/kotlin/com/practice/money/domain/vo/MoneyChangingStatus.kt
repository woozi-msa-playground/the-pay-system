package com.practice.money.domain.vo

enum class MoneyChangingStatus(
    val moneyChangingStatus: Int
) {
    REQUESTED(0),
    SUCCEEDED(1),
    FAILED(2),
    CANCELLED(3);

    companion object {
        fun of(moneyChangingStatus: Int): MoneyChangingStatus =
            values().firstOrNull { it.moneyChangingStatus == moneyChangingStatus } ?: REQUESTED
    }
}