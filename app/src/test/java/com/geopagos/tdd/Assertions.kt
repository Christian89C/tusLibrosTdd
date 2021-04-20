package com.geopagos.tdd

import org.junit.Assert

inline fun<reified T> assertExceptionIsThrown(exceptionMessage: String, collaborations: () -> Unit) {
    try {
        collaborations.invoke()
        Assert.fail()
    }
    catch (exception: Exception) {
        Assert.assertTrue(exception is T)
        Assert.assertEquals(exceptionMessage, exception.message)
    }
}