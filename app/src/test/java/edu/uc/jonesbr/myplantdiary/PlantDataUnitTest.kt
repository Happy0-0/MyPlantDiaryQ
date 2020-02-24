package edu.uc.jonesbr.myplantdiary

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import edu.uc.jonesbr.myplantdiary.dto.Plant
import edu.uc.jonesbr.myplantdiary.service.PlantService
import edu.uc.jonesbr.myplantdiary.ui.main.MainViewModel
import io.mockk.confirmVerified
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.junit.rules.TestRule

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class PlantDataUnitTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    lateinit var mvm: MainViewModel



    @Test
    fun confirmEasterRedBudOuputsEasternRedBud () {
        var plant: Plant = Plant("Cercis", "canadesis", "Eastern Redbud")
        assertEquals("Eastern Redbud",plant.toString());
    }

    @Test
    fun searchForRedbud_returnsRedbud() {
        givenAFeedOfPlantDataAreAvailable()
        whenSearchForRedbud()
        thenResultContainsEasternRedbud()

    }

     fun givenAFeedOfPlantDataAreAvailable() {
        mvm = MainViewModel()

    }



    private fun whenSearchForRedbud() {
        mvm.fetchPlants("redbud")
    }

    private fun thenResultContainsEasternRedbud() {
        var redbudFound = false;
        mvm.plants.observeForever {
            // /here is where we do the observing
            assertNotNull(it)
            assertTrue(it.size > 0)
            it.forEach {
                if (it.genus == "Cercis" && it.species == "canadensis" && it.common.contains("Eastern Redbud")) {
                    redbudFound = true
                }
            }
            assertTrue(redbudFound)
        }
    }

    @Test
    fun searchForGarbage_returnsNothing() {
        givenAFeedOfPlantDataAreAvailable()
        whenISearchForGarbage()
        thenIGetZeroResults()

    }

    private fun whenISearchForGarbage() {
        mvm.fetchPlants("sklujapouetllkjsdau")

    }

    private fun thenIGetZeroResults() {
        mvm.plants.observeForever {
            assertEquals(0, it.size)
        }
    }

}