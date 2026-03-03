package br.com.androidtest.features.myplan.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import br.com.androidtest.designsystem.theme.Ink500

@Composable
fun MyPlanHighlights(
    status: String,
    phone: String,
    plan: String,
    bonus: String
) {
    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        PlanRow(label = "Status", value = status)
        PlanRow(label = "Meu número", value = phone)
        PlanRow(label = "Plano", value = plan)
        PlanRow(label = "Bônus para redes sociais", value = bonus)
    }
}

@Composable
private fun PlanRow(label: String, value: String) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(text = "$label:", style = MaterialTheme.typography.bodySmall, color = Ink500)
        Text(text = value, style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.SemiBold)
    }
}
