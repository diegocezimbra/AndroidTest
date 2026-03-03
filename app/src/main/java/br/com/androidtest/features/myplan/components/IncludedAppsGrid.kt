package br.com.androidtest.features.myplan.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
        text = "Apps inclusos",
        style = MaterialTheme.typography.bodySmall,
        color = Ink500,
        modifier = Modifier.padding(top = 8.dp, bottom = 6.dp)
    )

    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(6.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, DividerGray)
    ) {
        LazyRow(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            items(appUrls) { appUrl ->
                AsyncImage(
                    model = appUrl,
                    contentDescription = "App",
                    modifier = Modifier.size(18.dp)
                )
            }
        }
    }
}
