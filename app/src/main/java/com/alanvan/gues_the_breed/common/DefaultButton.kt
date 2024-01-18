package com.alanvan.gues_the_breed.common

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun DefaultButton(title: String, onClicked: () -> Unit) {
    Button(
        modifier = Modifier
            .wrapContentSize()
            .padding(24.dp),
        onClick = onClicked
    ) {
        Text(text = title, style = MaterialTheme.typography.bodyLarge)
    }
}

@Preview
@Composable
fun DefaultButtonPreview() {
    DefaultButton(title = "Button title") {

    }
}
