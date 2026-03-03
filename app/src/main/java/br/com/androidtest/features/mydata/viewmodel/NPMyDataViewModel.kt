package br.com.androidtest.features.mydata.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.androidtest.common.DispatcherProvider
import br.com.androidtest.features.mydata.model.ExitModalUiModel
import br.com.androidtest.features.mydata.model.MyDataActionIcon
import br.com.androidtest.features.mydata.model.MyDataActionUiModel
import br.com.androidtest.features.mydata.model.MyDataScreenState
import br.com.androidtest.features.mydata.model.MyDataUiModel
import br.com.androidtest.repositories.mydata.MyDataRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NPMyDataViewModel(
    private val repository: MyDataRepository,
    private val dispatcherProvider: DispatcherProvider,
    private val loadingDelayMs: Long = DEFAULT_LOADING_DELAY_MS
) : ViewModel(), MyDataViewModelContract {

    companion object {
        const val DEFAULT_LOADING_DELAY_MS: Long = 2_000
    }

    private val _state = MutableStateFlow(MyDataScreenState())
    override val state: StateFlow<MyDataScreenState> = _state.asStateFlow()

    init {
        load(forceRefresh = false)
    }

    override fun load(forceRefresh: Boolean) {
        viewModelScope.launch(dispatcherProvider.io) {
            _state.value = MyDataScreenState(isLoading = true)
            runCatching {
                delay(loadingDelayMs)
                createUiModel(forceRefresh)
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

    private suspend fun createUiModel(forceRefresh: Boolean): MyDataUiModel {
        val domain = repository.getNewData(forceRefresh)
        return MyDataUiModel(
            title = domain.title,
            avatarUrl = domain.profile.avatarUrl,
            name = domain.profile.name,
            cpf = domain.profile.documentNumber,
            age = domain.profile.age,
            actions = domain.options.map { option ->
                MyDataActionUiModel(
                    icon = option.iconKey.toUiIcon(),
                    title = option.title,
                    actionType = option.actionType,
                    url = option.url,
                    assetName = option.assetName,
                    modal = option.modal?.let {
                        ExitModalUiModel(
                            title = it.title,
                            confirmTitle = it.confirmTitle,
                            dismissTitle = it.dismissTitle
                        )
                    }
                )
            }
        )
    }

    private fun String.toUiIcon(): MyDataActionIcon = when {
        contains("plan", ignoreCase = true) -> MyDataActionIcon.MY_PLAN
        contains("document", ignoreCase = true) || contains("download", ignoreCase = true) -> MyDataActionIcon.DOCUMENT
        contains("message", ignoreCase = true) -> MyDataActionIcon.PRIVACY
        contains("block", ignoreCase = true) -> MyDataActionIcon.EXIT
        else -> MyDataActionIcon.DOCUMENT
    }
}
