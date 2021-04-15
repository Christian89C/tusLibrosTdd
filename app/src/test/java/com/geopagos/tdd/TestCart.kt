package com.geopagos.tdd

import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertFalse
import org.junit.Test
import kotlin.IllegalArgumentException

class TestCart {
    private val otherBook = "SBN 23232323232"
    private val book = "SBN 12314214215"

    private fun cart(catalog: Catalog<String>) = Cart(catalog)

    private fun catalog(books: MutableSet<String>) = Catalog(books)

    private fun assertContainsBookQuantity(cartBooks: Cart<String>, book: String, quantity: Int = 1){
        assert(cartBooks.contains(book))
        assertEquals(quantity, cartBooks.quantity(book))
    }

    @Test
    fun createEmptyCart() {
        val catalog = catalog(mutableSetOf())
        val cart = cart(catalog)

        assert(cart.isEmpty())
    }


    @Test
    fun addBookToCart(){
        val catalog = catalog(mutableSetOf(book))
        val cart = cart(catalog)

        cart.add(book)

        assertFalse(cart.isEmpty())
        assertContainsBookQuantity(cart, book, 1)
    }

    @Test
    fun addMoreThanOneBookToCart(){
        val catalog = catalog(mutableSetOf(book))
        val cart = cart(catalog)

        cart.add(book, 3)

        assertContainsBookQuantity(cart, book, 3)
        assertFalse(cart.isEmpty())
    }

    @Test
    fun addOneBookManyTimes(){
        val catalog = catalog(mutableSetOf(book))
        val cart = cart(catalog)

        cart.add(book, 2)
        cart.add(book, 3)

        assertContainsBookQuantity(cart, book, 5)
        assertFalse(cart.isEmpty())
    }


    @Test
    fun addDifferentBooks(){
        val catalog = catalog(mutableSetOf(book, otherBook))
        val cart = cart(catalog)

        cart.add(book, 2)
        cart.add(otherBook, 3)

        assertContainsBookQuantity(cart, book, 2)
        assertContainsBookQuantity(cart, otherBook, 3)
        assertFalse(cart.isEmpty())
    }

    @Test(expected = IllegalArgumentException::class)
    fun quantityMustBePositive(){
        val catalog = catalog(mutableSetOf(book))
        val cart = cart(catalog)

        cart.add(book, -2)
    }

    @Test
    fun listCartBooks(){
        val catalog = catalog(mutableSetOf(book, otherBook))
        val cart = cart(catalog)

        cart.add(book, 2)
        cart.add(otherBook, 3)

        cart.listItems().let{ cartBooks ->
            assert(cartBooks.contains(book))
            assert(cartBooks.contains(otherBook))
            assertEquals(2, cartBooks[book])
            assertEquals(3, cartBooks[otherBook])
        }
    }

    @Test
    fun catalogContainsBookAdded(){
        val catalog = catalog(mutableSetOf(book))

        assert(catalog.contains(book))
    }

    @Test
    fun catalogDoesNotContainsBookNotAdded(){
        val catalog = catalog(mutableSetOf(book))

        assertFalse(catalog.contains(otherBook))
    }

    @Test(expected = IllegalArgumentException::class)
    fun addBookToCartNotInCatalog(){
        val catalog = catalog(mutableSetOf(book))
        val cart = cart(catalog)

        cart.add(otherBook)
    }

}
