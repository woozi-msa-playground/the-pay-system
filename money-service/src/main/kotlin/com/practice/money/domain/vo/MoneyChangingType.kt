package com.practice.money.domain.vo

enum class MoneyChangingType(
    val moneyChangingType: Int
) {
    INCREASING(0),
    DECREASING(1);

    companion object {
        fun of(moneyChangingType: Int): MoneyChangingType =
            values().firstOrNull { it.moneyChangingType == moneyChangingType } ?: INCREASING
    }
}