package br.com.androidtest.features.mydata.view

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.androidtest.common.ActionType
import br.com.androidtest.common.PlatformType
import br.com.androidtest.common.openUrl
import br.com.androidtest.common.shareAssetPdf
import br.com.androidtest.designsystem.molecules.ErrorState
import br.com.androidtest.designsystem.molecules.LoadingState
import br.com.androidtest.designsystem.organisms.ConfirmDialog
import br.com.androidtest.designsystem.theme.BrandRed
import br.com.androidtest.features.mydata.components.MyDataActionItem
import br.com.androidtest.features.mydata.components.MyDataProfileHeader
import br.com.androidtest.features.mydata.model.ExitModalUiModel
import br.com.androidtest.features.mydata.viewmodel.MyDataViewModelContract
import br.com.androidtest.features.mydata.viewmodel.NPMyDataViewModel
import br.com.androidtest.features.mydata.viewmodel.RWMyDataViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.qualifier.named

@Composable
fun MyDataScreen(
    platformType: PlatformType,
    onBack: () -> Unit,
    onOpenMyPlan: () -> Unit,
    onExitApp: () -> Unit
) {
    BackHandler(onBack = onBack)

    val viewModel: MyDataViewModelContract = when (platformType) {
        PlatformType.NP -> koinViewModel<NPMyDataViewModel>(
            qualifier = named(platformType.myDataQualifier)
        )

        PlatformType.RW -> koinViewModel<RWMyDataViewModel>(
            qualifier = named(platformType.myDataQualifier)
        )
    }

    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    var exitModal by remember { mutableStateOf<ExitModalUiModel?>(null) }

    exitModal?.let { modal ->
        ConfirmDialog(
            title = "Sair?",
            subtitle = modal.title,
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
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.surface)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                            .background(BrandRed)
                    )

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 16.dp),
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = "Meus dados",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )
                        MyDataProfileHeader(
                            name = model.name,
                            cpf = model.cpf,
                            age = model.age,
                            avatarUrl = model.avatarUrl
                        )
                    }

                    HorizontalDivider()

                    Column(verticalArrangement = Arrangement.spacedBy(0.dp)) {
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
                                                title = "Deseja realmente sair do app?",
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
