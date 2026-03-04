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
fun MyPlanHighlightsTop(
    status: String,
    phone: String,
    planValue: String
) {
    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Row {
                Text(text = "Status: ", style = MaterialTheme.typography.bodyMedium, color = Ink500)
                Text(text = status, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold)
            }
            Text(text = planValue, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold)
        }
        PlanRow(label = "Meu número:", value = phone)
    }
}

@Composable
fun MyPlanHighlightsBottom(
    plan: String,
    bonus: String
) {
    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        PlanRowNormal(label = "Plano", value = plan)
        PlanRowNormal(label = "Bônus para redes sociais", value = bonus)
    }
}

@Composable
private fun PlanRow(label: String, value: String) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(text = label, style = MaterialTheme.typography.bodyMedium, color = Ink500)
        Text(text = value, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold)
    }
}

@Composable
private fun PlanRowNormal(label: String, value: String) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(text = label, style = MaterialTheme.typography.bodyMedium, color = Ink500)
        Text(text = value, style = MaterialTheme.typography.bodyMedium)
    }
}
