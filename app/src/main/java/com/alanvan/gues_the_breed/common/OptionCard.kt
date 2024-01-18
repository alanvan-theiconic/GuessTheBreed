package com.alanvan.gues_the_breed.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.alanvan.gues_the_breed.ui.theme.typography

@Composable
fun OptionCard(modifier: Modifier, title: String, onClicked: () -> Unit) {
    Card(
        modifier = modifier
            .clickable {
                onClicked()
            },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .defaultMinSize(minHeight = 90.dp)
        ) {
            Text(text = title, style = typography.titleMedium)
        }
    }
}
