package br.com.androidtest.features.mydata.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.androidtest.common.ActionType
import br.com.androidtest.common.PlatformType
import br.com.androidtest.common.openUrl
import br.com.androidtest.common.shareAssetPdf
import br.com.androidtest.designsystem.molecules.ErrorState
import br.com.androidtest.designsystem.molecules.LoadingState
import br.com.androidtest.designsystem.organisms.AppCard
import br.com.androidtest.designsystem.organisms.AppTopBar
import br.com.androidtest.designsystem.organisms.ConfirmDialog
import br.com.androidtest.features.mydata.components.MyDataActionItem
import br.com.androidtest.features.mydata.components.MyDataProfileHeader
import br.com.androidtest.features.mydata.model.ExitModalUiModel
import br.com.androidtest.features.mydata.viewmodel.MyDataViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.qualifier.named

@Composable
fun MyDataScreen(
    platformType: PlatformType,
    onBack: () -> Unit,
    onOpenMyPlan: () -> Unit,
    onExitApp: () -> Unit
) {
    val viewModel = koinViewModel<MyDataViewModel>(qualifier = named(platformType.myDataQualifier))
    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    var exitModal by remember { mutableStateOf<ExitModalUiModel?>(null) }

    exitModal?.let { modal ->
        ConfirmDialog(
            title = modal.title,
            confirmText = modal.confirmTitle,
            dismissText = modal.dismissTitle,
            onConfirm = onExitApp,
            onDismiss = { exitModal = null }
        )
    }

    when {
        state.isLoading -> LoadingState()
        state.errorMessage != null -> ErrorState(
            message = state.errorMessage.orEmpty(),
            onRetry = { viewModel.load(forceRefresh = true) }
        )

        state.uiModel != null -> {
            state.uiModel?.let { model ->
                Scaffold(
                    topBar = {
                        AppTopBar(title = model.title, onBack = onBack)
                    }
                ) { paddingValues ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues)
                            .padding(horizontal = 16.dp, vertical = 12.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        AppCard {
                            MyDataProfileHeader(
                                name = model.name,
                                cpf = model.cpf,
                                age = model.age,
                                avatarUrl = model.avatarUrl
                            )

                            HorizontalDivider()

                            Column {
                                model.actions.forEach { action ->
                                    MyDataActionItem(
                                        icon = action.icon,
                                        title = action.title,
                                        onClick = {
                                            when (action.actionType) {
                                                ActionType.MY_PLAN -> onOpenMyPlan()
                                                ActionType.SHARE_CONTRACT -> {
                                                    shareAssetPdf(context, action.assetName ?: "terms.pdf")
                                                }

                                                ActionType.WEB -> {
                                                    action.url?.let { openUrl(context, it) }
                                                }

                                                ActionType.EXIT -> {
                                                    exitModal = action.modal ?: ExitModalUiModel(
                                                        title = "Deseja realmente sair?",
                                                        confirmTitle = "Sim",
                                                        dismissTitle = "Não"
                                                    )
                                                }

                                                ActionType.DISMISS,
                                                ActionType.BACK -> Unit
                                            }
                                        }
                                    )
                                    HorizontalDivider()
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
