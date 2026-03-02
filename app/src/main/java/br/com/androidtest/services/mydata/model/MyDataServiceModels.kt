package br.com.androidtest.services.mydata.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MyDataNewServiceModel(
    val screen: MyDataScreenServiceModel
)

@Serializable
data class MyDataScreenServiceModel(
    val title: String,
    val profile: MyDataProfileServiceModel,
    val options: List<MyDataOptionServiceModel>
)

@Serializable
data class MyDataProfileServiceModel(
    val id: String,
    val name: String,
    val age: String,
    val documentNumber: String,
    val avatarUrl: String
)

@Serializable
data class MyDataOptionServiceModel(
    val iconUrl: String? = null,
    val iconType: String? = null,
    val title: String,
    val action: String,
    val assetName: String? = null,
    val url: String? = null,
    val modal: ExitModalServiceModel? = null
)

@Serializable
data class ExitModalServiceModel(
    val title: String,
    val buttons: List<ExitButtonServiceModel>
)

@Serializable
data class ExitButtonServiceModel(
    val title: String,
    val action: String
)

@Serializable
data class MyDataOldServiceModel(
    val content: MyDataOldContentServiceModel
)

@Serializable
data class MyDataOldContentServiceModel(
    val user: MyDataProfileServiceModel
)
