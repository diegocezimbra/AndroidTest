package br.com.androidtest.features.myplan.model

data class MyPlanUiModel(
    val status: String,
    val phoneNumber: String,
    val planValue: String,
    val planName: String,
    val offerDisplay: String,
    val plan: String,
    val bonus: String,
    val includedApps: List<String>
)

data class MyPlanScreenState(
    val isLoading: Boolean = true,
    val uiModel: MyPlanUiModel? = null,
    val errorMessage: String? = null
)
