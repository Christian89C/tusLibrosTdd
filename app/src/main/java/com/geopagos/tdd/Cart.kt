package com.geopagos.tdd

class Cart<T>(private val catalog: Catalog<T>) {
    private val items: MutableMap<T, Int> = HashMap()

    fun isEmpty(): Boolean{
        return items.isEmpty()
    }

    fun add(item: T, quantity: Int = 1) {
        if (quantity <= 0){
            throw IllegalArgumentException("book quantity must be a positive number")
        } else if (!catalog.contains(item)){
            throw IllegalArgumentException("book must be in the catalog")
        }
        items[item] = quantity(item) + quantity
    }

    fun contains(item: T): Boolean {
        return items.contains(item)
    }

    fun quantity(item: T): Int {
        return items.getOrDefault(item, 0)
    }

    fun listItems(): Map<T, Int> {
        return items
    }
}
