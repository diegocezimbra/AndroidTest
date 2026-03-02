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
import br.com.androidtest.designsystem.theme.Ink600

@Composable
fun MyPlanHighlights(
    status: String,
    phone: String,
    plan: String,
    bonus: String
) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        PlanRow(label = "Status", value = status)
        PlanRow(label = "Meu número", value = phone)

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = "Plano", style = MaterialTheme.typography.bodyMedium, color = Ink600)
                Text(text = plan, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            }
            Column(modifier = Modifier.weight(1f)) {
                Text(text = "Bônus", style = MaterialTheme.typography.bodyMedium, color = Ink600)
                Text(text = bonus, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
private fun PlanRow(label: String, value: String) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(text = label, style = MaterialTheme.typography.bodyMedium, color = Ink600)
        Text(text = value, style = MaterialTheme.typography.titleMedium)
    }
}
