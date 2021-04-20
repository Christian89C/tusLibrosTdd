package com.geopagos.tdd


class Catalog<T>(private val items: Set<CatalogItem<T>>) {

    companion object {
        const val EXCEPTION_ITEM_NOT_PRESENT = "item is not present"
    }

    fun contains(book: T): Boolean {
        return items.any { it.item == book }
    }

    fun priceOf(item: T): Double {
        val catalogItem = items.find { it.item == item } ?: throw IllegalArgumentException(EXCEPTION_ITEM_NOT_PRESENT)
        return catalogItem.price
    }

}
