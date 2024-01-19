package com.alanvan.gues_the_breed.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun OptionCard(
    modifier: Modifier,
    title: String,
    textAlign: TextAlign = TextAlign.Start,
    colors: CardColors = CardDefaults.cardColors(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.onPrimaryContainer
    ),
    contentPadding: PaddingValues = PaddingValues(16.dp),
    onClicked: () -> Unit
) {
    Card(
        modifier = modifier
            .clickable {
                onClicked()
            },
        colors = colors
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.padding(contentPadding)
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = title,
                textAlign = textAlign,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}
