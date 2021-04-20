package com.geopagos.tdd

interface TransactionProcessor {

    fun process(aCard: Card, amount: Double): String

}
