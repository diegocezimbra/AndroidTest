package br.com.androidtest.features.mydata.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.androidtest.common.DispatcherProvider
import br.com.androidtest.features.mydata.model.MyDataScreenState
import br.com.androidtest.features.mydata.model.MyDataUiModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

abstract class MyDataViewModel(
    private val dispatcherProvider: DispatcherProvider,
    private val loadingDelayMs: Long = 2_000
) : ViewModel() {

    private val _state = MutableStateFlow(MyDataScreenState())
    val state: StateFlow<MyDataScreenState> = _state.asStateFlow()

    init {
        load()
    }

    fun load(forceRefresh: Boolean = false) {
        viewModelScope.launch(dispatcherProvider.io) {
            _state.value = MyDataScreenState(isLoading = true)
            runCatching {
                delay(loadingDelayMs)
                buildUiModel(forceRefresh)
            }.onSuccess { model ->
                _state.value = MyDataScreenState(
                    isLoading = false,
                    uiModel = model
                )
            }.onFailure { throwable ->
                _state.value = MyDataScreenState(
                    isLoading = false,
                    errorMessage = throwable.message ?: "Erro ao carregar dados"
                )
            }
        }
    }

    protected abstract suspend fun buildUiModel(forceRefresh: Boolean): MyDataUiModel
}
