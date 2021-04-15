package com.geopagos.tdd

import org.junit.Test

import org.junit.Assert.*

class TestXX {

    @Test
    fun test1To10() {
        assertEquals("I", convertToRomanString(1))
        assertEquals("II", convertToRomanString(2))
        assertEquals("III", convertToRomanString(3))
        assertEquals("IV", convertToRomanString(4))
        assertEquals("V", convertToRomanString(5))
        assertEquals("VI", convertToRomanString(6))
        assertEquals("VII", convertToRomanString(7))
        assertEquals("VIII", convertToRomanString(8))
        assertEquals("IX", convertToRomanString(9))
        assertEquals("X", convertToRomanString(10))
    }

    @Test
    fun testA() {
        assertEquals("XI", convertToRomanString(11))
        assertEquals("XII", convertToRomanString(12))
        assertEquals("XIII", convertToRomanString(13))
    }

    @Test
    fun testB() {
        assertEquals("XIV", convertToRomanString(14))
    }


    @Test
    fun testC() {
        assertEquals("XV", convertToRomanString(15))
        assertEquals("XVI", convertToRomanString(16))
        assertEquals("XVII", convertToRomanString(17))
        assertEquals("XVIII", convertToRomanString(18))
    }

    @Test
    fun testD() {
        assertEquals("XX", convertToRomanString(20))
        assertEquals("XXIII", convertToRomanString(23))
        assertEquals("XXIV", convertToRomanString(24))
        assertEquals("XXV", convertToRomanString(25))
        assertEquals("XXVIII", convertToRomanString(28))
        assertEquals("XXIX", convertToRomanString(29))
    }

    @Test
    fun testE() {
        assertEquals("XXX", convertToRomanString(30))
        assertEquals("XXXIII", convertToRomanString(33))
        assertEquals("XXXIV", convertToRomanString(34))
        assertEquals("XXXV", convertToRomanString(35))
        assertEquals("XXXVIII", convertToRomanString(38))
        assertEquals("XXXIX", convertToRomanString(39))
    }

    @Test
    fun testF() {
        assertEquals("XL", convertToRomanString(40))
        assertEquals("XLIII", convertToRomanString(43))
        assertEquals("XLIV", convertToRomanString(44))
        assertEquals("XLV", convertToRomanString(45))
        assertEquals("XLVIII", convertToRomanString(48))
        assertEquals("XLIX", convertToRomanString(49))
    }

    @Test
    fun testG() {
        assertEquals("L", convertToRomanString(50))
        assertEquals("LIII", convertToRomanString(53))
        assertEquals("LIV", convertToRomanString(54))
        assertEquals("LV", convertToRomanString(55))
        assertEquals("LVIII", convertToRomanString(58))
        assertEquals("LIX", convertToRomanString(59))
    }

    @Test
    fun testH() {
        assertEquals("LX", convertToRomanString(60))
        assertEquals("LXIII", convertToRomanString(63))
        assertEquals("LXIV", convertToRomanString(64))
        assertEquals("LXV", convertToRomanString(65))
        assertEquals("LXVIII", convertToRomanString(68))
        assertEquals("LXIX", convertToRomanString(69))
    }

    @Test
    fun testI() {
        assertEquals("LXX", convertToRomanString(70))
        assertEquals("LXXIII", convertToRomanString(73))
        assertEquals("LXXIV", convertToRomanString(74))
        assertEquals("LXXV", convertToRomanString(75))
        assertEquals("LXXVIII", convertToRomanString(78))
        assertEquals("LXXIX", convertToRomanString(79))
    }


    @Test
    fun testJ() {
        assertEquals("LXXX", convertToRomanString(80))
        assertEquals("LXXXIII", convertToRomanString(83))
        assertEquals("LXXXIV", convertToRomanString(84))
        assertEquals("LXXXV", convertToRomanString(85))
        assertEquals("LXXXVIII", convertToRomanString(88))
        assertEquals("LXXXIX", convertToRomanString(89))
    }

    private fun convertToRomanString(number: Int): String {
        val decena = number / 10
        var romanNumber = convertDigit(number - (decena * 10))

        if (decena >= 1 && decena < 4) {//because of swift
            for (i in 1..decena) {
                romanNumber = "X" + romanNumber
            }
        }

        if (decena == 4) {
            romanNumber = "XL" + romanNumber
        }

        if (decena == 5) {
            romanNumber = "L" + romanNumber
        }

        if (decena == 6) {
            romanNumber = "LX" + romanNumber
        }

        if (decena == 7) {
            romanNumber = "LXX" + romanNumber
        }

        if (decena == 8) {
            romanNumber = "LXXX" + romanNumber
        }

        return romanNumber
    }

    private fun convertDigit(number: Int): String {
        var romanNumber = ""
        if (number in 1..3) {
            romanNumber = getUnities(number)
        }
        if (number == 4) {
            romanNumber = "IV"
        }
        if (number == 5) {
            romanNumber = "V"
        }
        if (number > 5) {
            romanNumber = "V" + getUnities(number-5)
        }
        if (number == 9) {
            romanNumber = "IX";
        }
        return romanNumber
    }

    private fun getUnities(number: Int): String {
        var romanNumber = ""
        for (i in 1..number) {
            romanNumber = romanNumber + "I"
        }
        return romanNumber
    }

}