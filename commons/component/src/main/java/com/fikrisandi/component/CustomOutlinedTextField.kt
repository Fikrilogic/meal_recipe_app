package com.fikrisandi.component

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.fikrisandi.theme.AppColors
import com.fikrisandi.theme.AppShape

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomOutlinedTextField(
    modifier: Modifier = Modifier,
    value: String,
    onChange: (String) -> Unit,
    placeholder: @Composable () -> Unit = {},
    label: @Composable () -> Unit = {},
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Done,
    shape: Shape = AppShape.large,
    readOnly: Boolean = false,
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors(
        focusedBorderColor = AppColors.primary,
        unfocusedBorderColor = AppColors.primary.copy(alpha = .5f),
        focusedTrailingIconColor = AppColors.primary,
        unfocusedTrailingIconColor = AppColors.primary,
        focusedTextColor = AppColors.primary,
        unfocusedTextColor = AppColors.primary,
    ),
    trailingIcon: @Composable () -> Unit = {},
    errorMessage: String = "",
) {
    OutlinedTextField(
        value = value,
        onValueChange = onChange,
        modifier = modifier,
        placeholder = placeholder,
        label = label,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = imeAction
        ),
        readOnly = readOnly,
        shape = shape,
        colors = colors,
        trailingIcon = trailingIcon,
        supportingText = {
            Text(errorMessage, color = AppColors.error)
        },
        isError = errorMessage.isNotEmpty()
    )
}