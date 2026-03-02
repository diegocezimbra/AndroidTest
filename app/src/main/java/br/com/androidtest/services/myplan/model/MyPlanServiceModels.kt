package br.com.androidtest.services.myplan.model

import kotlinx.serialization.Serializable

@Serializable
data class MyPlanNewServiceModel(
    val screen: MyPlanScreenServiceModel
)

@Serializable
data class MyPlanScreenServiceModel(
    val name: String,
    val offerDisplay: String,
    val planValue: String,
    val header: List<List<HeaderEntryServiceModel>>,
    val extraPlay: ExtraPlayServiceModel
)

@Serializable
data class HeaderEntryServiceModel(
    val title: String,
    val value: HeaderValueServiceModel
)

@Serializable
data class HeaderValueServiceModel(
    val text: String
)

@Serializable
data class ExtraPlayServiceModel(
    val title: String,
    val options: List<ExtraPlayOptionServiceModel>
)

@Serializable
data class ExtraPlayOptionServiceModel(
    val description: String,
    val url: String
)

@Serializable
data class MyPlanOldServiceModel(
    val content: MyPlanOldContentServiceModel
)

@Serializable
data class MyPlanOldContentServiceModel(
    val planValue: String,
    val status: String,
    val phoneNumber: String,
    val plan: Int,
    val bonus: Int,
    val extraPlayBaseUrl: String,
    val extraPlay: String
)
