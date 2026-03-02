package br.com.androidtest.features.myplan.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.androidtest.common.PlatformType
import br.com.androidtest.designsystem.molecules.ErrorState
import br.com.androidtest.designsystem.molecules.LoadingState
import br.com.androidtest.designsystem.organisms.AppCard
import br.com.androidtest.designsystem.organisms.AppTopBar
import br.com.androidtest.features.myplan.components.IncludedAppsList
import br.com.androidtest.features.myplan.components.MyPlanHighlights
import br.com.androidtest.features.myplan.viewmodel.MyPlanViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.qualifier.named

@Composable
fun MyPlanScreen(
    platformType: PlatformType,
    onBack: () -> Unit
) {
    val viewModel = koinViewModel<MyPlanViewModel>(qualifier = named(platformType.myPlanQualifier))
    val state by viewModel.state.collectAsStateWithLifecycle()

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
                        AppTopBar(title = "Meu plano", onBack = onBack)
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
                            MyPlanHighlights(
                                status = model.status,
                                phone = model.phoneNumber,
                                plan = model.plan,
                                bonus = model.bonus
                            )
                        }

                        AppCard {
                            IncludedAppsList(appUrls = model.includedApps)
                        }
                    }
                }
            }
        }
    }
}
