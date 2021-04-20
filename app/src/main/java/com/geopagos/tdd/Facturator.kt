package com.geopagos.tdd

class Facturator<T>(private val catalog: Catalog<T>) {

    fun totalFor(aCart: Cart<T>): Double {
        return aCart.listItems().toList().fold(0.0) { total, entry ->
            val item = entry.first
            val quantity = entry.second
            total + (catalog.priceOf(item) * quantity)
        }
    }

}