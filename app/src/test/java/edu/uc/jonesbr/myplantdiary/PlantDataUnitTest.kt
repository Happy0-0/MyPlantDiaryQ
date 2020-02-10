package edu.uc.jonesbr.myplantdiary

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import edu.uc.jonesbr.myplantdiary.dto.Plant
import edu.uc.jonesbr.myplantdiary.service.PlantService
import edu.uc.jonesbr.myplantdiary.ui.main.MainViewModel
import io.mockk.every
import io.mockk.mockk
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

    var plantService = mockk<PlantService>()

    @Test
    fun confirmEasterRedBudOuputsEasternRedBud () {
        var plant: Plant = Plant("Cercis", "canadesis", "Eastern Redbud")
        assertEquals("Eastern Redbud",plant.toString());
    }

    @Test
    fun searchForRedbud_returnsRedbud() {
        givenAFeedOfMockedPlantDataAreAvailable()
        whenSearchForRedbud()
        thenResultContainsEasternRedbud()
    }

     fun givenAFeedOfMockedPlantDataAreAvailable() {
        mvm = MainViewModel()
        createMockData()
    }

     fun createMockData() {
        var allPlantsLiveData = MutableLiveData<ArrayList<Plant>>()
        var allPlants = ArrayList<Plant>()
        //create and add plants to our collection
        var redbud = Plant("Cercis", "canadesis", "Eastern Redbud")
        allPlants.add(redbud)
        var redOak = Plant("Cercis", "canadesis", "Eastern Redbud")
        allPlants.add(redOak)
        var whiteOak = Plant("Cercis", "canadesis", "Eastern Redbud")
        allPlants.add(whiteOak)
        allPlantsLiveData.postValue(allPlants)
        every {plantService.fetchPlants(or("Redbud", "Quercus"))} returns allPlantsLiveData
        every {plantService.fetchPlants(not(or("Redbud", "Quercus")))} returns MutableLiveData()
        mvm.plantService = plantService
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
        givenAFeedOfMockedPlantDataAreAvailable()
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