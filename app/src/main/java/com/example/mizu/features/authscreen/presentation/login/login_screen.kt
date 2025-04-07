package com.example.mizu.features.authscreen.presentation.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Visibility
import androidx.compose.material.icons.rounded.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mizu.R
import com.example.mizu.features.authscreen.utils.LoginData
import com.example.mizu.features.authscreen.utils.SignUpData
import com.example.mizu.features.onboarding.utils.TextFieldCustom
import com.example.mizu.features.onboarding.utils.TextFieldCustomIcon
import com.example.mizu.ui.theme.backgroundColor2
import com.example.mizu.ui.theme.fontFamily
import com.example.mizu.ui.theme.fontFamilyLight
import com.example.mizu.ui.theme.mizuBlack
import com.example.mizu.ui.theme.onboardingBoxColor
import com.example.mizu.ui.theme.waterColor
import com.example.mizu.ui.theme.waterColorBackground

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    loginData: LoginData,
    getEmailChange: (String) -> Unit,
    getPasswordChange: (String) -> Unit,
    getLogin: () -> Unit,
    getForgotPassword: () -> Unit,
    getLoginWithGoogle: () -> Unit,
    getSignUpNavigate: () -> Unit,
) {
    var onPasswordVisible by remember {
        mutableStateOf(false)
    }
    LazyColumn(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Column(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(horizontal = 16.dp), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_login_screen),
                    contentDescription = "Login Screen Icon",
                    modifier = Modifier.size(180.dp)
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "Welcome to Mizu",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontFamily = fontFamily,
                        fontWeight = FontWeight(400),
                        color = mizuBlack,
                        textAlign = TextAlign.Center,
                    ), modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Please Login so that we can keep track of your hydration",
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = fontFamily,
                        fontWeight = FontWeight(200),
                        color = mizuBlack,
                        textAlign = TextAlign.Center
                    ), modifier = Modifier.fillMaxWidth()
                )
            }
        }
        item {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        onboardingBoxColor.copy(alpha = 0.5f),
                        shape = RoundedCornerShape(topEnd = 30.dp, topStart = 30.dp)
                    )
                    .padding(horizontal = 16.dp, vertical = 20.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                TextFieldCustom(
                    onTextChange = loginData.onEmail,
                    checkError = loginData.onEmailCheckError,
                    onErrorText = loginData.onEmailError,
                    onPlaceHolderText = "Enter Email",
                    getTextChange = getEmailChange,
                    onLabelText = "Email",
                    onImeAction = ImeAction.Next
                )
                Spacer(modifier = Modifier.height(10.dp))
                TextFieldCustomIcon(
                    onTextChange = loginData.onPassword,
                    checkError = loginData.onPasswordCheckError,
                    onErrorText = loginData.onPasswordError,
                    onPlaceHolderText = "Enter Password",
                    getTextChange = getPasswordChange,
                    onLabelText = "Password",
                    onImeAction = ImeAction.Done,
                    visualTransformation = if (onPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    onKeyboardType = KeyboardType.Password,
                    onTrailingIcon = {
                        if (!onPasswordVisible) {
                            Icon(
                                imageVector = Icons.Rounded.VisibilityOff,
                                contentDescription = "VisibilityOffIcon",
                                modifier = Modifier.clickable(interactionSource = remember {
                                    MutableInteractionSource()
                                }, indication = null, onClick = {
                                    onPasswordVisible = true
                                })
                            )
                        } else {
                            Icon(
                                imageVector = Icons.Rounded.Visibility,
                                contentDescription = "VisibilityIcon",
                                modifier = Modifier.clickable(interactionSource = remember {
                                    MutableInteractionSource()
                                }, indication = null, onClick = {
                                    onPasswordVisible = false
                                })
                            )
                        }
                    }
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Forgot Password?",
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontFamily = fontFamily,
                        fontWeight = FontWeight(400),
                        color = mizuBlack,
                        textAlign = TextAlign.End
                    ), modifier = Modifier
                        .fillMaxWidth().clickable(interactionSource = remember {
                            MutableInteractionSource()
                        }, indication = null, onClick = {
                            getForgotPassword()
                        })
                )

                Box(
                    modifier = Modifier
                        .clickable(interactionSource = remember {
                            MutableInteractionSource()
                        }, indication = null, onClick = {
                            getLogin()
                        })
                        .fillMaxWidth()
                        .height(90.dp)
                        .padding(horizontal = 20.dp, vertical = 20.dp)
                        .background(waterColor, shape = RoundedCornerShape(6.dp)),
                ) {
                    Text(
                        text = "Login",
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontFamily = fontFamilyLight,
                            fontWeight = FontWeight(400),
                            color = mizuBlack,
                            textAlign = TextAlign.Center,
                        ), modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.Center)
                    )
                }

                Spacer(modifier = Modifier.height(30.dp))

                Text(
                    text = "or Login With",
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontFamily = fontFamilyLight,
                        fontWeight = FontWeight(400),
                        color = mizuBlack,
                        textAlign = TextAlign.Center
                    ), modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(4.dp))

                Box(
                    modifier = Modifier
                        .clickable(interactionSource = remember {
                            MutableInteractionSource()
                        }, indication = null, onClick = {
                            getLoginWithGoogle()
                        })
                        .fillMaxWidth()
                        .height(70.dp)
                        .padding(horizontal = 20.dp, vertical = 10.dp)
                        .background(onboardingBoxColor, shape = RoundedCornerShape(6.dp)),
                ) {
                    Row(
                        modifier = Modifier.align(Alignment.Center),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Image(
                            imageVector = ImageVector.vectorResource(R.drawable.ic_google),
                            contentDescription = "Google Login",
                            modifier = Modifier.size(30.dp)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "Login with Google",
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontFamily = fontFamilyLight,
                                fontWeight = FontWeight(400),
                                color = mizuBlack,
                                textAlign = TextAlign.Center,
                            ), modifier = Modifier
                        )
                    }

                }

                Spacer(modifier = Modifier.height(40.dp))


                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Don't have an account?",
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = fontFamily,
                            fontWeight = FontWeight(400),
                            color = mizuBlack,
                            textAlign = TextAlign.Center
                        ), modifier = Modifier
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    Text(
                        text = "Sign Up",
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = fontFamily,
                            fontWeight = FontWeight(600),
                            color = mizuBlack,
                            textAlign = TextAlign.Center
                        ), modifier = Modifier.clickable(interactionSource = remember {
                            MutableInteractionSource()
                        }, indication = null, onClick = {
                            getSignUpNavigate()
                        })
                    )
                }
            }
        }

    }
}


@Preview(showBackground = true)
@Composable
fun PreviewCompScreen() {
    LoginScreen(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.linearGradient(
                    start = Offset(Float.POSITIVE_INFINITY * 0.4f, 0f),
                    end = Offset(0f, Float.POSITIVE_INFINITY),
                    colors = listOf(waterColorBackground, backgroundColor2)
                )
            ),
        loginData = LoginData(
            onEmail = "",
            onEmailError = "",
            onEmailCheckError = false,
            onPassword = "",
            onPasswordCheckError = false,
            onPasswordError = ""
        ),
        getEmailChange = {},
        getForgotPassword = {},
        getLogin = {},
        getPasswordChange = {},
        getLoginWithGoogle = {},
        getSignUpNavigate = {}
    )
}