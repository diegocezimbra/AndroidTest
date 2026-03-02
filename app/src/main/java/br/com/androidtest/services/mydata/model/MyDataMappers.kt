package br.com.androidtest.services.mydata.model

import br.com.androidtest.common.ActionType
import br.com.androidtest.repositories.mydata.model.ExitModalDomainModel
import br.com.androidtest.repositories.mydata.model.MyDataNewDomainModel
import br.com.androidtest.repositories.mydata.model.MyDataOldDomainModel
import br.com.androidtest.repositories.mydata.model.MyDataOptionDomainModel
import br.com.androidtest.repositories.mydata.model.MyDataProfileDomainModel

fun MyDataNewServiceModel.map(): MyDataNewDomainModel = MyDataNewDomainModel(
    title = screen.title,
    profile = screen.profile.map(),
    options = screen.options.map { it.map() }
)

fun MyDataOldServiceModel.map(): MyDataOldDomainModel = MyDataOldDomainModel(
    profile = content.user.map()
)

private fun MyDataProfileServiceModel.map(): MyDataProfileDomainModel = MyDataProfileDomainModel(
    id = id,
    name = name,
    age = age,
    documentNumber = documentNumber,
    avatarUrl = avatarUrl
)

private fun MyDataOptionServiceModel.map(): MyDataOptionDomainModel = MyDataOptionDomainModel(
    iconKey = iconType ?: iconUrl.orEmpty(),
    title = title,
    actionType = action.toActionType(),
    assetName = assetName,
    url = url,
    modal = modal?.map()
)

private fun ExitModalServiceModel.map(): ExitModalDomainModel {
    val confirm = buttons.firstOrNull { it.action.equals("EXIT", ignoreCase = true) }?.title ?: "Sim"
    val dismiss = buttons.firstOrNull { it.action.equals("DISMISS", ignoreCase = true) }?.title ?: "Não"
    return ExitModalDomainModel(
        title = title,
        confirmTitle = confirm,
        dismissTitle = dismiss
    )
}

private fun String.toActionType(): ActionType =
    runCatching { ActionType.valueOf(this.uppercase()) }.getOrDefault(ActionType.DISMISS)
