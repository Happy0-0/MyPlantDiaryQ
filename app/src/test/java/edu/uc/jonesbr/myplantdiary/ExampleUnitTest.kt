package edu.uc.jonesbr.myplantdiary

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun addition_isNotCorrect() {
        assertEquals(3, 1 + 2)
    }

    fun addTwoAndThree_equalsFive() {
        assertEquals(5, 2 + 3)
    }

    fun addTwoAndThree_equalsSix() {
        assertEquals(6, 3 + 3)
    }

    fun addFiveAndThree_equalsEight() {
        assertEquals(6, 5 + 3)
    }
    fun confirmEasterRedBudOuputsEasternRedBud () {
        var plant:Plant = Plant("Cercis", "canadesis", " Eastern Redbud")
        assertEquals("Eastern Redbud",plant.toString());
    }
}
