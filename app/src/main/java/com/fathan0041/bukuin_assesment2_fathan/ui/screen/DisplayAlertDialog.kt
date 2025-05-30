package com.fathan0041.bukuin_assesment2_fathan.ui.screen

import android.content.res.Configuration
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.fathan0041.bukuin_assesment2_fathan.R
import com.fathan0041.bukuin_assesment2_fathan.ui.theme.BukuIn_Assesment2_FathanTheme

@Composable
fun DisplayAlertDialog(
    onDismissRequest:() -> Unit,
    onConfirmation: () -> Unit
){
    AlertDialog(
        text = { Text(text = stringResource(R.string.pesan_hapus)) },
        confirmButton = {
            TextButton(onClick = {onConfirmation()}) {
                Text(text = stringResource(R.string.tombol_hapus))
            }
        },
        dismissButton = {
            TextButton(onClick = {onDismissRequest()}) {
                Text(text = stringResource(R.string.tombol_batal))
            }
        },
        onDismissRequest = {onDismissRequest()}
    )

}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun DialogPreview() {
    BukuIn_Assesment2_FathanTheme(themeId = 0) {
        DisplayAlertDialog(
            onDismissRequest = {},
            onConfirmation = {}
        )
    }
}