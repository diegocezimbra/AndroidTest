package br.com.androidtest.features.myplan.viewmodel

import br.com.androidtest.repositories.myplan.MyPlanRepository
import br.com.androidtest.repositories.myplan.model.MyPlanNewDomainModel
import br.com.androidtest.repositories.myplan.model.MyPlanOldDomainModel
import br.com.androidtest.testutils.MainDispatcherRule
import br.com.androidtest.testutils.TestDispatcherProvider
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class RWMyPlanViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun `should expose legacy ui model with calculated fields`() = runTest {
        val dispatcher = StandardTestDispatcher(testScheduler)
        val repository = object : MyPlanRepository {
            override suspend fun getNewData(forceRefresh: Boolean): MyPlanNewDomainModel {
                error("Not needed")
            }

            override suspend fun getOldData(forceRefresh: Boolean): MyPlanOldDomainModel {
                return MyPlanOldDomainModel(
                    status = "Active",
                    phoneNumber = "(11) 99999-0000",
                    planValue = "R$ 39,99",
                    plan = 15,
                    bonus = 5,
                    extraPlayBaseUrl = "https://cdn.apps",
                    extraPlay = listOf("instagram.png", "youtube.png")
                )
            }
        }

        val viewModel = RWMyPlanViewModel(
            repository = repository,
            dispatcherProvider = TestDispatcherProvider(dispatcher),
            loadingDelayMs = 0
        )

        advanceUntilIdle()

        val state = viewModel.state.value
        assertFalse(state.isLoading)
        assertEquals("20GB", state.uiModel?.offerDisplay)
        assertEquals("15GB", state.uiModel?.plan)
        assertEquals("5GB", state.uiModel?.bonus)
        assertEquals(2, state.uiModel?.includedApps?.size)
        assertEquals("https://cdn.apps/instagram.png", state.uiModel?.includedApps?.first())
    }
}
