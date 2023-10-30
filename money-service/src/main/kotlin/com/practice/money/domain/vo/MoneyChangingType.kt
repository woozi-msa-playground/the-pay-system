package com.practice.money.domain.vo

enum class MoneyChangingType(
    val moneyChangingType: Int
) {
    INITIALISING(0),
    INCREASING(1),
    DECREASING(2);

    companion object {
        fun of(moneyChangingType: Int): MoneyChangingType =
            values().firstOrNull { it.moneyChangingType == moneyChangingType } ?: INCREASING
    }
}