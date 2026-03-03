package br.com.androidtest.features.mydata.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.androidtest.common.ActionType
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

class RWMyDataViewModel(
    private val repository: MyDataRepository,
    private val privacyUrl: String,
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
        val domain = repository.getOldData(forceRefresh)
        return MyDataUiModel(
            title = "Meus dados",
            avatarUrl = domain.profile.avatarUrl,
            name = domain.profile.name,
            cpf = domain.profile.documentNumber,
            age = domain.profile.age,
            actions = listOf(
                MyDataActionUiModel(
                    icon = MyDataActionIcon.MY_PLAN,
                    title = "Meu plano",
                    actionType = ActionType.MY_PLAN
                ),
                MyDataActionUiModel(
                    icon = MyDataActionIcon.DOCUMENT,
                    title = "Baixar contrato",
                    actionType = ActionType.SHARE_CONTRACT,
                    assetName = "terms.pdf"
                ),
                MyDataActionUiModel(
                    icon = MyDataActionIcon.PRIVACY,
                    title = "Privacidade",
                    actionType = ActionType.WEB,
                    url = privacyUrl
                ),
                MyDataActionUiModel(
                    icon = MyDataActionIcon.EXIT,
                    title = "Sair",
                    actionType = ActionType.EXIT,
                    modal = ExitModalUiModel(
                        title = "Deseja realmente sair?",
                        confirmTitle = "Sim",
                        dismissTitle = "Não"
                    )
                )
            )
        )
    }
}
