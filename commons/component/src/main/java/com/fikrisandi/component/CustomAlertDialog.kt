package com.fikrisandi.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.fikrisandi.theme.AppColors
import com.fikrisandi.theme.AppShape
import com.fikrisandi.theme.AppTypography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomSquareAlert(
    modifier: Modifier = Modifier,
    message: String,
    show: Boolean,
    status: Boolean = false,
    onClose: () -> Unit
) {
    if (show) {
        AlertDialog(modifier = modifier.padding(horizontal = 16.dp), onDismissRequest = {}) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth(.4f)
                    .heightIn(max = 250.dp),
                shape = MaterialTheme.shapes.large,
                tonalElevation = AlertDialogDefaults.TonalElevation,
                color = AppColors.secondaryContainer
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxHeight()
                            .weight(.6f)
                    ) {
                        Column(modifier = Modifier
                            .fillMaxWidth()
                            .weight(.3f)) {
                            Text(
                                text = if (status) "Proses Berhasil" else "Proses Gagal",
                                style = AppTypography.displayMedium,
                                color = AppColors.onBackground
                            )
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(.6f),
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = message,
                                style = AppTypography.bodyLarge,
                                color = AppColors.onBackground,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                    OutlinedButton(
                        modifier = Modifier.align(Alignment.End),
                        onClick = onClose,
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = AppColors.primaryContainer,
                            contentColor = AppColors.background,
                        ),
                        border = BorderStroke(1.dp, color = AppColors.primary),
                        shape = AppShape.small
                    ) {
                        Text("Tutup", color = AppColors.onBackground)
                    }
                }
            }
        }
    }
}