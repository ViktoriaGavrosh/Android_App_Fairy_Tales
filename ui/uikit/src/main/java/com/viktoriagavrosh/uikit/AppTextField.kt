package com.viktoriagavrosh.uikit

import android.content.res.Configuration
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.viktoriagavrosh.uitheme.FairyTalesTheme

@Composable
fun AppTextField(
    text: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String = "",
) {
    TextField(
        value = text,
        onValueChange = onValueChange,
        textStyle = MaterialTheme.typography.bodyLarge,
        label = {
            if (label.isNotEmpty()) {
                Text(
                    text = label,
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
        },
        modifier = modifier,
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = MaterialTheme.colorScheme.onPrimary,
            focusedContainerColor = MaterialTheme.colorScheme.onPrimary,
        )
    )
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun AppTextFieldPreview() {
    FairyTalesTheme {
        AppTextField(
            text = "Text",
            onValueChange = {},
        )
    }
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun WithLabelAppTextFieldPreview() {
    FairyTalesTheme {
        AppTextField(
            text = "Text",
            onValueChange = {},
            label = "Label",
        )
    }
}
