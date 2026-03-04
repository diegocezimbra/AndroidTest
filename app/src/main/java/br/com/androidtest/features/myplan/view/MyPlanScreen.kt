package br.com.androidtest.features.myplan.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.androidtest.common.PlatformType
import br.com.androidtest.designsystem.molecules.ErrorState
import br.com.androidtest.designsystem.molecules.LoadingState
import br.com.androidtest.designsystem.theme.BrandRed
import br.com.androidtest.features.myplan.components.IncludedAppsList
import br.com.androidtest.features.myplan.components.MyPlanHighlightsBottom
import br.com.androidtest.features.myplan.components.MyPlanHighlightsTop
import br.com.androidtest.features.myplan.viewmodel.MyPlanViewModelContract
import br.com.androidtest.features.myplan.viewmodel.NPMyPlanViewModel
import br.com.androidtest.features.myplan.viewmodel.RWMyPlanViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.qualifier.named

@Composable
fun MyPlanScreen(
    platformType: PlatformType,
    onBack: () -> Unit
) {
    val viewModel: MyPlanViewModelContract = when (platformType) {
        PlatformType.NP -> koinViewModel<NPMyPlanViewModel>(
            qualifier = named(platformType.myPlanQualifier)
        )

        PlatformType.RW -> koinViewModel<RWMyPlanViewModel>(
            qualifier = named(platformType.myPlanQualifier)
        )
    }

    val state by viewModel.state.collectAsStateWithLifecycle()

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
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                            .background(BrandRed)
                            .padding(horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Voltar",
                            tint = MaterialTheme.colorScheme.surface,
                            modifier = Modifier
                                .size(24.dp)
                                .clickable(onClick = onBack)
                        )
                    }

                    Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.Top
                        ) {
                            Text(
                                text = model.planName,
                                modifier = Modifier.weight(1f),
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = model.offerDisplay,
                                style = MaterialTheme.typography.headlineLarge,
                                color = BrandRed,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        MyPlanHighlightsTop(
                            status = model.status,
                            phone = model.phoneNumber,
                            planValue = model.planValue
                        )

                        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

                        MyPlanHighlightsBottom(
                            plan = model.plan,
                            bonus = model.bonus
                        )

                        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

                        IncludedAppsList(appUrls = model.includedApps)
                    }
                }
            }
        }
    }
}
