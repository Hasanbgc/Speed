package com.apps.speed.presentation.component

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ExitAlertDialog() {
    AlertDialog(
        onDismissRequest = {},
        confirmButton = {},
        modifier = Modifier,
        dismissButton = {},
        icon = {},
        title = { Text(text = "Are You Confirm To Exit?") },
        shape = RoundedCornerShape(16.dp),
        )
}