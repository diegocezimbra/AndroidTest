package br.com.androidtest.features.mydata.viewmodel

import br.com.androidtest.common.DispatcherProvider
import br.com.androidtest.features.mydata.model.ExitModalUiModel
import br.com.androidtest.features.mydata.model.MyDataActionIcon
import br.com.androidtest.features.mydata.model.MyDataActionUiModel
import br.com.androidtest.features.mydata.model.MyDataUiModel
import br.com.androidtest.repositories.mydata.MyDataRepository

class NPMyDataViewModel(
    private val repository: MyDataRepository,
    dispatcherProvider: DispatcherProvider,
    loadingDelayMs: Long = 2_000
) : MyDataViewModel(
    dispatcherProvider = dispatcherProvider,
    loadingDelayMs = loadingDelayMs
) {

    override suspend fun buildUiModel(forceRefresh: Boolean): MyDataUiModel {
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
