package com.example.mizu.features.onboarding.utils

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mizu.ui.theme.circleWaterIndicatorColor
import com.example.mizu.ui.theme.fontFamilyLight
import com.example.mizu.ui.theme.mizuBlack
import com.example.mizu.ui.theme.mizuBlackLight
import com.example.mizu.ui.theme.onboardingBoxColor
import com.example.mizu.ui.theme.textFieldColor
import com.example.mizu.ui.theme.textFieldErrorColor
import com.example.mizu.ui.theme.waterColorMeter

@Composable
fun TextFieldCustom(
    modifier: Modifier = Modifier,
    onTextChange: String,
    checkError: Boolean,
    onErrorText: String,
    onPlaceHolderText: String,
    onLabelText:String,
    getTextChange: (String) -> Unit,
    onImeAction: ImeAction,
    visualTransformation: VisualTransformation? = null,
    onKeyboardType: KeyboardType? = null
) {
    val focusManager = LocalFocusManager.current

    Column(
        modifier = modifier
            .fillMaxWidth().wrapContentSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = onTextChange,
            onValueChange = { getTextChange(it) },
            modifier = Modifier
                .wrapContentSize()
                .fillMaxWidth().padding(0.dp),
            isError = checkError,
            maxLines = 1,
            shape = RoundedCornerShape(10.dp),
            colors = OutlinedTextFieldDefaults.colors(
                cursorColor = waterColorMeter,
                errorCursorColor = textFieldErrorColor,
                errorBorderColor = textFieldErrorColor,
                focusedLabelColor = mizuBlack,
                focusedTextColor = mizuBlack,
                unfocusedBorderColor = mizuBlack.copy(alpha = 0.5f),
                focusedBorderColor = mizuBlack.copy(alpha = 0.6f),
                disabledLabelColor = Color.Transparent,
                disabledContainerColor = onboardingBoxColor.copy(alpha = 0.5f),
                unfocusedContainerColor = onboardingBoxColor.copy(alpha = 0.8f),
                focusedContainerColor = onboardingBoxColor,
            ),
            placeholder = {
                Text(
                    text = onPlaceHolderText,
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = fontFamilyLight,
                        fontWeight = FontWeight(400),
                        color = mizuBlack.copy(alpha = 0.5f),
                        textAlign = TextAlign.Center
                    )
                )
            },
            label = {
                Text(
                    text = onLabelText,
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = fontFamilyLight,
                        fontWeight = FontWeight(100),
                        color = mizuBlackLight,
                        textAlign = TextAlign.Center
                    )
                )
            },
            textStyle = TextStyle(
                fontSize = 14.sp,
                fontFamily = fontFamilyLight,
                fontWeight = FontWeight(400),
                color = mizuBlack,
                textAlign = TextAlign.Start
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = onKeyboardType?:KeyboardType.Text,
                imeAction = onImeAction
            ),
            visualTransformation = visualTransformation?:VisualTransformation.None,
        )
        if (checkError){
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = onErrorText,
                style = TextStyle(
                    fontSize = 10.sp,
                    fontFamily = fontFamilyLight,
                    fontWeight = FontWeight(200),
                    color = textFieldErrorColor,
                    textAlign = TextAlign.Center,
                ), modifier = Modifier.fillMaxWidth()
            )
        }

    }
}

@Preview(showSystemUi = false, showBackground = true)
@Composable
fun PreviewTextField() {
    var text by remember {
        mutableStateOf("")
    }
    TextFieldCustom(
        onTextChange = text,
        checkError = false,
        onErrorText = "TextError",
        onPlaceHolderText = "Enter the Text",
        getTextChange = {
            text = it
        }, onLabelText = "Text", onImeAction = ImeAction.Done
    )
}