package br.com.androidtest.features.mydata.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import br.com.androidtest.designsystem.theme.BrandRed
import br.com.androidtest.designsystem.theme.Ink500

@Composable
fun MyDataProfileHeader(
    name: String,
    cpf: String,
    age: String,
    avatarUrl: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Box(
            contentAlignment = Alignment.BottomEnd,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 24.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(96.dp)
                    .clip(CircleShape)
                    .border(3.dp, BrandRed, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null,
                    tint = BrandRed,
                    modifier = Modifier.size(48.dp)
                )
            }

            Box(
                modifier = Modifier
                    .size(24.dp)
                    .shadow(4.dp, CircleShape)
                    .background(color = MaterialTheme.colorScheme.surface, shape = CircleShape)
            )
        }

        Text(
            text = buildAnnotatedString {
                append("Nome: ")
                withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                    append(name)
                }
            },
            style = MaterialTheme.typography.bodyLarge
        )

        Text(
            text = buildAnnotatedString {
                append("Cpf: ")
                withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                    append(cpf)
                }
            },
            style = MaterialTheme.typography.bodyLarge
        )

        Text(
            text = "Idade: $age",
            style = MaterialTheme.typography.bodyLarge,
            color = Ink500
        )
    }
}
