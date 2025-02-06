package com.example.mizu.features.authscreen.presentation.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mizu.R
import com.example.mizu.features.authscreen.utils.LoginData
import com.example.mizu.features.authscreen.utils.SignUpData
import com.example.mizu.features.onboarding.utils.TextFieldCustom
import com.example.mizu.ui.theme.fontFamily
import com.example.mizu.ui.theme.fontFamilyLight
import com.example.mizu.ui.theme.mizuBlack
import com.example.mizu.ui.theme.onboardingBoxColor
import com.example.mizu.ui.theme.waterColor

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    loginData: LoginData,
    getEmailChange: (String) -> Unit,
    getPasswordChange: (String) -> Unit,
    getLogin: () -> Unit,
    getForgotPassword: () -> Unit,
    getLoginWithGoogle: () -> Unit,
    getSignUpNavigate: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .wrapContentSize()
                .padding(horizontal = 16.dp)
                .weight(0.5f)
        ) {
            Row(modifier=Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start) {

                Image(
                    imageVector = ImageVector.vectorResource(R.drawable.logo_mizu),
                    contentDescription = "loginScreenMizuLogo",
                    modifier = Modifier.size(50.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = "MyNetWater",
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = fontFamily,
                        fontWeight = FontWeight(600),
                        color = mizuBlack,
                        textAlign = TextAlign.Center,
                    ), modifier = Modifier
                )
            }
            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Login In",
                style = TextStyle(
                    fontSize = 24.sp,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight(600),
                    color = mizuBlack,
                    textAlign = TextAlign.Center,
                ), modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Please Login so that we can keep track of your hydration",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight(200),
                    color = mizuBlack,
                    textAlign = TextAlign.Center
                ), modifier = Modifier.fillMaxWidth()
            )
        }



        Column(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
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
                    getNavigate = {},
                    onLabelText = "Email"
                )
                Spacer(modifier = Modifier.height(10.dp))
                TextFieldCustom(
                    onTextChange = loginData.onPassword,
                    checkError = loginData.onPasswordCheckError,
                    onErrorText = loginData.onPasswordError,
                    onPlaceHolderText = "Enter Password",
                    getTextChange = getPasswordChange,
                    getNavigate = {},
                    onLabelText = "Password"
                )

                Spacer(modifier = Modifier.height(4.dp))
                
                Text(
                    text = "Forgot Password?",
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = fontFamily,
                        fontWeight = FontWeight(400),
                        color = mizuBlack,
                        textAlign = TextAlign.End
                    ), modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            getForgotPassword()
                        }
                )

                Box(
                    modifier = Modifier
                        .clickable {
                            getLogin()
                        }
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
                        .clickable {
                            getLogin()
                        }
                        .fillMaxWidth()
                        .height(70.dp)
                        .padding(horizontal = 20.dp, vertical = 10.dp)
                        .background(onboardingBoxColor, shape = RoundedCornerShape(6.dp)),
                ) {
                    Text(
                        text = "Login with Google",
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
                    ), modifier = Modifier
                )
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
                    start = Offset(Float.POSITIVE_INFINITY, 0f),
                    end = Offset(0f, Float.POSITIVE_INFINITY),
                    colors = listOf(waterColor.copy(alpha = 0.6f), mizuBlack.copy(alpha = 0.3f))
                ),
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