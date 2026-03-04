package br.com.androidtest.features.mydata.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Block
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.PrivacyTip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import br.com.androidtest.designsystem.theme.BrandRed
import br.com.androidtest.features.mydata.model.MyDataActionIcon

@Composable
fun MyDataActionItem(
    icon: MyDataActionIcon,
    title: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon.imageVector(),
                contentDescription = null,
                tint = BrandRed,
                modifier = Modifier.size(24.dp)
            )
            Text(text = title, style = MaterialTheme.typography.bodyLarge)
        }
        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
    }
}

private fun MyDataActionIcon.imageVector(): ImageVector = when (this) {
    MyDataActionIcon.MY_PLAN -> Icons.Default.CreditCard
    MyDataActionIcon.DOCUMENT -> Icons.Default.Description
    MyDataActionIcon.PRIVACY -> Icons.Default.PrivacyTip
    MyDataActionIcon.EXIT -> Icons.Default.Block
}
