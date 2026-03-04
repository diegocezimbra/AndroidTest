package br.com.androidtest.features.main.view

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
            title = "Sair?",
            subtitle = "Deseja realmente sair do app?",
            confirmText = "Sim",
            dismissText = "Não",
            onConfirm = onExitApp,
            onDismiss = { showExitDialog = false }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .padding(horizontal = 20.dp, vertical = 36.dp)
    ) {
        Text(
            text = "PRINCIPAL",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(start = 4.dp, bottom = 8.dp)
        )

        Surface(
            shape = RoundedCornerShape(2.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(360.dp)
        ) {
            Column {
                Spacer(modifier = Modifier.weight(1f))
                HorizontalDivider()
                PlatformRow(
                    title = "Plataforma Nova",
                    onClick = { onOpenPlatform(PlatformType.NP) }
                )
                HorizontalDivider()
                PlatformRow(
                    title = "Plataforma Antiga",
                    onClick = { onOpenPlatform(PlatformType.RW) }
                )
            }
        }
    }
}

@Composable
private fun PlatformRow(
    title: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 10.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = title, style = MaterialTheme.typography.titleMedium)
        Icon(imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = null)
    }
}
