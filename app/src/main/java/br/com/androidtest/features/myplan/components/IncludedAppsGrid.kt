package br.com.androidtest.features.myplan.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.androidtest.designsystem.theme.DividerGray
import br.com.androidtest.designsystem.theme.Ink500
import coil.compose.AsyncImage

@Composable
fun IncludedAppsList(appUrls: List<String>) {
    Text(
        text = "Apps inclusos:",
        style = MaterialTheme.typography.bodyMedium,
        color = Ink500,
        modifier = Modifier.padding(top = 12.dp, bottom = 8.dp)
    )

    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, DividerGray)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            appUrls.forEach { appUrl ->
                AsyncImage(
                    model = appUrl,
                    contentDescription = "App",
                    modifier = Modifier.size(32.dp)
                )
            }
        }
    }
}
