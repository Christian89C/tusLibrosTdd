package com.geopagos.tdd

class Cashier<T>(private val txProcessor: TransactionProcessor, private val facturator: Facturator<T>) {

    companion object {
        const val EXCEPTION_EMPTY_CART = "cannot checkout an empty cart"
        const val EXCEPTION_EXPIRED_CARD = "cannot checkout with an expired card"
    }


    fun checkout(aCart: Cart<T>, aCard: Card): String {
        if (aCard.isExpired()) throw IllegalArgumentException(EXCEPTION_EXPIRED_CARD)
        if(aCart.isEmpty()) throw IllegalArgumentException(EXCEPTION_EMPTY_CART)

        val txAmount = facturator.totalFor(aCart)
        return txProcessor.process(aCard, txAmount)
    }



}
