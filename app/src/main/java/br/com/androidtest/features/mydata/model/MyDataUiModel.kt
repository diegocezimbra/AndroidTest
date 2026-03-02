package br.com.androidtest.features.mydata.model

import br.com.androidtest.common.ActionType

data class MyDataUiModel(
    val title: String,
    val avatarUrl: String,
    val name: String,
    val cpf: String,
    val age: String,
    val actions: List<MyDataActionUiModel>
)

data class MyDataActionUiModel(
    val icon: MyDataActionIcon,
    val title: String,
    val actionType: ActionType,
    val url: String? = null,
    val assetName: String? = null,
    val modal: ExitModalUiModel? = null
)

enum class MyDataActionIcon {
    MY_PLAN,
    DOCUMENT,
    PRIVACY,
    EXIT
}

data class ExitModalUiModel(
    val title: String,
    val confirmTitle: String,
    val dismissTitle: String
)

data class MyDataScreenState(
    val isLoading: Boolean = true,
    val uiModel: MyDataUiModel? = null,
    val errorMessage: String? = null
)
