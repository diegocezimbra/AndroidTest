package br.com.androidtest.features.mydata.viewmodel

import br.com.androidtest.common.ActionType
import br.com.androidtest.common.DispatcherProvider
import br.com.androidtest.features.mydata.model.ExitModalUiModel
import br.com.androidtest.features.mydata.model.MyDataActionIcon
import br.com.androidtest.features.mydata.model.MyDataActionUiModel
import br.com.androidtest.features.mydata.model.MyDataUiModel
import br.com.androidtest.repositories.mydata.MyDataRepository

class RWMyDataViewModel(
    private val repository: MyDataRepository,
    private val privacyUrl: String,
    dispatcherProvider: DispatcherProvider,
    loadingDelayMs: Long = 2_000
) : MyDataViewModel(
    dispatcherProvider = dispatcherProvider,
    loadingDelayMs = loadingDelayMs
) {

    override suspend fun buildUiModel(forceRefresh: Boolean): MyDataUiModel {
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
