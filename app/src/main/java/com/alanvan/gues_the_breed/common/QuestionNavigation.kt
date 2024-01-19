package com.alanvan.gues_the_breed.common

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun QuestionNavigation(
    modifier: Modifier = Modifier,
    text: String,
    onClicked: () -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth().wrapContentHeight()
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Button(
            modifier = Modifier
                .widthIn(120.dp),
            onClick = onClicked
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
