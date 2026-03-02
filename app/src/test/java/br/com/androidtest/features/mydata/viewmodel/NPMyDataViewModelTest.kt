package br.com.androidtest.features.mydata.viewmodel

import br.com.androidtest.common.ActionType
import br.com.androidtest.repositories.mydata.MyDataRepository
import br.com.androidtest.repositories.mydata.model.MyDataNewDomainModel
import br.com.androidtest.repositories.mydata.model.MyDataOldDomainModel
import br.com.androidtest.repositories.mydata.model.MyDataOptionDomainModel
import br.com.androidtest.repositories.mydata.model.MyDataProfileDomainModel
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
class NPMyDataViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun `should expose mapped ui state for new platform`() = runTest {
        val dispatcher = StandardTestDispatcher(testScheduler)
        val repository = object : MyDataRepository {
            override suspend fun getNewData(forceRefresh: Boolean): MyDataNewDomainModel {
                return MyDataNewDomainModel(
                    title = "Meus dados",
                    profile = MyDataProfileDomainModel(
                        id = "1",
                        name = "Zé",
                        age = "34",
                        documentNumber = "123",
                        avatarUrl = "https://avatar"
                    ),
                    options = listOf(
                        MyDataOptionDomainModel(
                            iconKey = "ic_myplan",
                            title = "Meu plano",
                            actionType = ActionType.MY_PLAN
                        )
                    )
                )
            }

            override suspend fun getOldData(forceRefresh: Boolean): MyDataOldDomainModel {
                error("Not needed")
            }
        }

        val viewModel = NPMyDataViewModel(
            repository = repository,
            dispatcherProvider = TestDispatcherProvider(dispatcher),
            loadingDelayMs = 0
        )

        advanceUntilIdle()

        val state = viewModel.state.value
        assertFalse(state.isLoading)
        assertEquals("Meus dados", state.uiModel?.title)
        assertEquals("Zé", state.uiModel?.name)
        assertEquals(1, state.uiModel?.actions?.size)
    }
}
