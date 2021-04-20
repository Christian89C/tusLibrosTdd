package com.geopagos.tdd

import java.util.Date

//todo_ Check valid card
class Card(private val datePassedChecker: DatePassedChecker, private val expirationDate: Date) {

    fun isExpired(): Boolean {
        return datePassedChecker.hasPassed(expirationDate)
    }

}
