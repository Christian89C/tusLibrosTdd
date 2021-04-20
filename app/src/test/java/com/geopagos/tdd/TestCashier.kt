package com.geopagos.tdd



import org.junit.Assert.*
import org.junit.Test
import java.time.Instant
import java.util.*
import kotlin.IllegalArgumentException

class TestCashier {

    private fun provideCart(aCatalog: Catalog<String> = provideCatalog(),
                            items: List<String> = listOf()): Cart<String> {
        return Cart(aCatalog).apply {
            items.forEach { this.add(it) }
        }
    }

    private fun provideCatalog(items: Set<CatalogItem<String>> = setOf()) = Catalog(items)

    private fun provideFacturator(aCatalog: Catalog<String> = provideCatalog()) = Facturator(aCatalog)

    private fun provideCashier(aTxProcessor: TransactionProcessor = provideTransactionProcessor(),
                                aFacturator: Facturator<String> = provideFacturator()) = Cashier<String>(aTxProcessor, aFacturator)

    private fun provideCard(isExpired: Boolean, date: Date = Date.from(Instant.now())): Card {
        var datePassedChecker = object : DatePassedChecker {
            override fun hasPassed(dateTime: Date): Boolean {
                return isExpired
            }
        }
        return Card(datePassedChecker, date)
    }

    private fun provideTransactionProcessor(txId: String = "1"): TransactionProcessorSimulator {
        return TransactionProcessorSimulator(txId)
    }

    @Test
    fun cannotCheckoutAnEmptyCart() {
        val aCashier = provideCashier()
        val aCart = provideCart(provideCatalog())
        val aCard = provideCard(isExpired = false)

        assertExceptionIsThrown<IllegalArgumentException>(Cashier.EXCEPTION_EMPTY_CART) {
            aCashier.checkout(aCart, aCard)
        }
    }

    @Test
    fun cannotCheckoutWithExpiredCard() {
        val aCashier = provideCashier()
        val aCart = provideCart()
        val aCard = provideCard(isExpired = true)

        assertExceptionIsThrown<IllegalArgumentException>(Cashier.EXCEPTION_EXPIRED_CARD) {
            aCashier.checkout(aCart, aCard)
        }
    }

    @Test
    fun checkoutIsAskedToTransactionProcessorWithRequestedCard() {
        val catalogItems = setOf(CatalogItem("abook", 10.0))
        val aCatalog = provideCatalog(catalogItems)
        val cartItems = catalogItems.toList().map { it.item }
        val aCart = provideCart(aCatalog, cartItems)
        val aCard = provideCard(isExpired = false)
        val testtxId = "100"
        val aTransactionProcessor = provideTransactionProcessor(txId = testtxId)
        val aCashier = provideCashier(aTxProcessor = aTransactionProcessor,
                                        aFacturator = provideFacturator(aCatalog))

        val resultTransactionId: String = aCashier.checkout(aCart, aCard)

        assertEquals(testtxId, resultTransactionId)
        assertEquals(1, aTransactionProcessor.processCalls.size)
        aTransactionProcessor.processCalls.let {
            val processCall = it[0]
            assertEquals(aCard, processCall.first)
        }
    }

    @Test
    fun checkoutIsAskedToTransactionProcessorWithCartPrice() {
        val catalogItems = setOf(CatalogItem("abook", 11.0))
        val aCatalog = provideCatalog(catalogItems)
        val cartItems = catalogItems.toList().map { it.item }
        val aCart = provideCart(aCatalog, cartItems)
        val aCard = provideCard(isExpired = false)
        val aTransactionProcessor = provideTransactionProcessor()
        val aCashier = provideCashier(aTxProcessor = aTransactionProcessor,
                aFacturator = provideFacturator(aCatalog))

        aCashier.checkout(aCart, aCard)

        aTransactionProcessor.processCalls.let {
            val processCall = it[0]
            assertEquals(11.0, processCall.second, 0.0)
        }
    }

    @Test
    fun checkoutIsAskedToTransactionProcessorWithCartWithTwoItemsPrice() {
        val catalogItems = setOf(CatalogItem("abook", 11.0), CatalogItem("otherbook", 10.0))
        val aCatalog = provideCatalog(catalogItems)
        val cartItems = catalogItems.toList().map { it.item }
        val aCart = provideCart(aCatalog, cartItems)
        val aCard = provideCard(isExpired = false)
        val aTransactionProcessor = provideTransactionProcessor()
        val aCashier = provideCashier(aTxProcessor = aTransactionProcessor,
                aFacturator = provideFacturator(aCatalog))

        aCashier.checkout(aCart, aCard)

        aTransactionProcessor.processCalls.let {
            val processCall = it[0]
            assertEquals(21.0, processCall.second, 0.0)
        }
    }


    @Test
    fun checkoutIsAskedToTransactionProcessorWithCartWithItemPresentMoreThanOneTimePrice() {
        val catalogItems = setOf(CatalogItem("abook", 11.0))
        val aCatalog = provideCatalog(catalogItems)
        val cartItems = catalogItems.toMutableList().map { it.item } + catalogItems.toMutableList().map { it.item }
        val aCart = provideCart(aCatalog, cartItems)
        val aCard = provideCard(isExpired = false)
        val aTransactionProcessor = provideTransactionProcessor()
        val aCashier = provideCashier(aTxProcessor = aTransactionProcessor,
                aFacturator = provideFacturator(aCatalog))

        aCashier.checkout(aCart, aCard)

        aTransactionProcessor.processCalls.let {
            val processCall = it[0]
            assertEquals(22.0, processCall.second, 0.0)
        }
    }








}