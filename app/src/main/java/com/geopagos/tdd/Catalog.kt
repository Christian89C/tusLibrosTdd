package com.geopagos.tdd

class Catalog<T>(private val items: Set<T>) {

    fun contains(book: T): Boolean {
        return items.contains(book)
    }

}
