package br.com.androidtest.features.myplan.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.androidtest.common.DispatcherProvider
import br.com.androidtest.features.myplan.model.MyPlanScreenState
import br.com.androidtest.features.myplan.model.MyPlanUiModel
import br.com.androidtest.repositories.myplan.MyPlanRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NPMyPlanViewModel(
    private val repository: MyPlanRepository,
    private val dispatcherProvider: DispatcherProvider,
    private val loadingDelayMs: Long = DEFAULT_LOADING_DELAY_MS
) : ViewModel(), MyPlanViewModelContract {

    companion object {
        const val DEFAULT_LOADING_DELAY_MS: Long = 2_000
    }

    private val _state = MutableStateFlow(MyPlanScreenState())
    override val state: StateFlow<MyPlanScreenState> = _state.asStateFlow()

    init {
        load(forceRefresh = false)
    }

    override fun load(forceRefresh: Boolean) {
        viewModelScope.launch(dispatcherProvider.io) {
            _state.value = MyPlanScreenState(isLoading = true)
            runCatching {
                delay(loadingDelayMs)
                createUiModel(forceRefresh)
            }.onSuccess { model ->
                _state.value = MyPlanScreenState(
                    isLoading = false,
                    uiModel = model
                )
            }.onFailure { throwable ->
                _state.value = MyPlanScreenState(
                    isLoading = false,
                    errorMessage = throwable.message ?: "Erro ao carregar plano"
                )
            }
        }
    }

    private suspend fun createUiModel(forceRefresh: Boolean): MyPlanUiModel {
        val domain = repository.getNewData(forceRefresh)
        return MyPlanUiModel(
            status = domain.status.toLocalizedPlanStatus(),
            phoneNumber = domain.phoneNumber,
            planValue = domain.planValue,
            planName = domain.planName,
            offerDisplay = domain.offerDisplay,
            plan = domain.plan,
            bonus = domain.bonus,
            includedApps = domain.apps
        )
    }
}
