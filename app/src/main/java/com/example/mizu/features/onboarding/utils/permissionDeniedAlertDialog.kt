package com.example.mizu.features.onboarding.utils

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.mizu.ui.theme.fontFamily
import com.example.mizu.ui.theme.mizuBlackLight
import com.example.mizu.ui.theme.waterColorMeter

@Composable
fun PermissionDeniedAlertDialog(
    getDismissButton: () -> Unit,
    getConfirmButton: () -> Unit,
    getAppSettings: () -> Unit,
    onPermissionTitle: String,
    onPermissionText: String,
    onPermanentlyDenied: Boolean,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        modifier = modifier,
        icon = {
            Icon(
                imageVector = Icons.Rounded.Notifications,
                contentDescription = "Notifications",
                tint = waterColorMeter
            )
        },
        onDismissRequest = { getDismissButton() },
        confirmButton = {
            if (!onPermanentlyDenied) {
                SingleButton(getNavigate = {
                    getAppSettings()
                }, buttonName = "Grant Permission")
            }else{
                SingleButton(getNavigate = {
                    getConfirmButton()
                }, buttonName = "Okay")
            }
        },
        title = {
            Text(
                text = onPermissionTitle,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight(600),
                    color = mizuBlackLight,
                    textAlign = TextAlign.Center,
                ), modifier = Modifier.fillMaxWidth()
            )
        },
        text = {
            Text(
                text = onPermissionText,
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight(400),
                    color = mizuBlackLight,
                    textAlign = TextAlign.Center,
                ), modifier = Modifier.fillMaxWidth()
            )
        },
    )
}