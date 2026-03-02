package br.com.androidtest.features.myplan.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.androidtest.common.DispatcherProvider
import br.com.androidtest.features.myplan.model.MyPlanScreenState
import br.com.androidtest.features.myplan.model.MyPlanUiModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

abstract class MyPlanViewModel(
    private val dispatcherProvider: DispatcherProvider,
    private val loadingDelayMs: Long = 2_000
) : ViewModel() {

    private val _state = MutableStateFlow(MyPlanScreenState())
    val state: StateFlow<MyPlanScreenState> = _state.asStateFlow()

    init {
        load()
    }

    fun load(forceRefresh: Boolean = false) {
        viewModelScope.launch(dispatcherProvider.io) {
            _state.value = MyPlanScreenState(isLoading = true)
            runCatching {
                delay(loadingDelayMs)
                buildUiModel(forceRefresh)
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

    protected abstract suspend fun buildUiModel(forceRefresh: Boolean): MyPlanUiModel
}
