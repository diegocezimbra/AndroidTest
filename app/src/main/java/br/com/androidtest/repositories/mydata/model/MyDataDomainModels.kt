package br.com.androidtest.repositories.mydata.model

import br.com.androidtest.common.ActionType

data class MyDataProfileDomainModel(
    val id: String,
    val name: String,
    val age: String,
    val documentNumber: String,
    val avatarUrl: String
)

data class ExitModalDomainModel(
    val title: String,
    val confirmTitle: String,
    val dismissTitle: String
)

data class MyDataOptionDomainModel(
    val iconKey: String,
    val title: String,
    val actionType: ActionType,
    val assetName: String? = null,
    val url: String? = null,
    val modal: ExitModalDomainModel? = null
)

data class MyDataNewDomainModel(
    val title: String,
    val profile: MyDataProfileDomainModel,
    val options: List<MyDataOptionDomainModel>
)

data class MyDataOldDomainModel(
    val profile: MyDataProfileDomainModel
)
