package br.com.androidtest.features.mydata.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import br.com.androidtest.designsystem.theme.Ink600
import coil.compose.AsyncImage

@Composable
fun MyDataProfileHeader(
    name: String,
    cpf: String,
    age: String,
    avatarUrl: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = avatarUrl,
            contentDescription = "Avatar",
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
        )

        Column {
            Text(text = name, style = MaterialTheme.typography.titleMedium)
            Text(text = cpf, style = MaterialTheme.typography.bodyMedium, color = Ink600)
            Text(text = "$age anos", style = MaterialTheme.typography.bodyMedium, color = Ink600)
        }
    }
}
