package br.com.androidtest.features.main.view

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.androidtest.common.PlatformType
import br.com.androidtest.designsystem.atoms.PrimaryButton
import br.com.androidtest.designsystem.organisms.ConfirmDialog

@Composable
fun MainScreen(
    onOpenPlatform: (PlatformType) -> Unit,
    onExitApp: () -> Unit
) {
    var showExitDialog by remember { mutableStateOf(false) }

    BackHandler {
        showExitDialog = true
    }

    if (showExitDialog) {
        ConfirmDialog(
            title = "Deseja realmente sair?",
            confirmText = "Sim",
            dismissText = "Não",
            onConfirm = onExitApp,
            onDismiss = { showExitDialog = false }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Escolha a plataforma",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        PrimaryButton(
            text = "Plataforma Nova",
            onClick = { onOpenPlatform(PlatformType.NP) }
        )

        PrimaryButton(
            text = "Plataforma Antiga",
            onClick = { onOpenPlatform(PlatformType.RW) },
            modifier = Modifier.padding(top = 12.dp)
        )
    }
}
