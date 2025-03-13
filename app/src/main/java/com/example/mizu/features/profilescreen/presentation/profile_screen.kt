import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.outlined.ArrowForwardIos
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mizu.R
import com.example.mizu.features.onboarding.utils.TextFieldCustom
import com.example.mizu.features.profilescreen.utils.ProfileData
import com.example.mizu.ui.theme.backgroundColor2
import com.example.mizu.ui.theme.fontFamily
import com.example.mizu.ui.theme.mizuBlack
import com.example.mizu.ui.theme.waterColorMeter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    getBack: () -> Unit,
    profileData: ProfileData,
    getNameChange: (String) -> Unit,
    getNavigate: () -> Unit,
    getEmailChange: (String) -> Unit,
    getNotificationChange: (Boolean) -> Unit,
    getNotificationIntervals: () -> Unit,
    getBugReport: () -> Unit
) {
    Column(
        modifier = modifier.padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .background(color = backgroundColor2, shape = RoundedCornerShape(100.dp))
                .width(150.dp)
                .height(150.dp)
                .border(
                    width = 1.dp,
                    color = mizuBlack.copy(alpha = 0.2f),
                    shape = RoundedCornerShape(100.dp)
                )
        ) {
            Image(
                imageVector = ImageVector.vectorResource(R.drawable.profiledefault),
                contentDescription = "Default Profile",
                modifier = Modifier.align(Alignment.Center)
            )
        }

        Spacer(modifier = Modifier.height(25.dp))

        TextFieldCustom(
            onTextChange = profileData.onNameChange,
            checkError = profileData.onNameCheck,
            onErrorText = "Error",
            onPlaceHolderText = "Enter Name",
            getTextChange = getNameChange,
            onLabelText = "Name",
            onImeAction = ImeAction.Next
        )
        Spacer(modifier = Modifier.height(5.dp))

        TextFieldCustom(
            onTextChange = profileData.onEmailChange,
            checkError = profileData.onEmailCheck,
            onErrorText = profileData.onEmailError,
            onPlaceHolderText = "Enter Email",
            getTextChange = getEmailChange,
            onLabelText = "Email",
            onImeAction = ImeAction.Done
        )
        Spacer(modifier = Modifier.height(25.dp))


        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = "Notifications",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight(400),
                    color = mizuBlack,
                    textAlign = TextAlign.Center,
                ), modifier = Modifier
            )
            Spacer(modifier = Modifier.width(10.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(color = mizuBlack.copy(alpha = 0.5f))
            )
        }
        Spacer(modifier = Modifier.height(25.dp))


        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Notifications",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight(400),
                    color = mizuBlack,
                    textAlign = TextAlign.Center,
                ), modifier = Modifier
            )
            Spacer(modifier = Modifier.width(10.dp))
            Switch(
                checked = profileData.onNotificationChange,
                onCheckedChange = getNotificationChange,
                thumbContent = if (profileData.onNotificationChange) {
                    {
                        Icon(
                            imageVector = Icons.Filled.Check,
                            contentDescription = "Notification",
                            modifier = Modifier.size(SwitchDefaults.IconSize)
                        )
                    }
                } else {
                    null
                }
            )
        }
        Spacer(modifier = Modifier.height(25.dp))


        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = "History",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight(400),
                    color = mizuBlack,
                    textAlign = TextAlign.Center,
                ), modifier = Modifier
            )
            Spacer(modifier = Modifier.width(10.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(color = mizuBlack.copy(alpha = 0.5f))
            )
        }

        Spacer(modifier = Modifier.height(25.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Report a Bug",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight(400),
                    color = mizuBlack,
                    textAlign = TextAlign.Center,
                ), modifier = Modifier
            )
            Spacer(modifier = Modifier.width(10.dp))
            Icon(
                imageVector = Icons.Outlined.ArrowForwardIos,
                contentDescription = "Report a Bug",
                modifier = Modifier.size(SwitchDefaults.IconSize)
            )
        }

        Spacer(modifier = Modifier.height(25.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Notification Intervals",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight(400),
                    color = mizuBlack,
                    textAlign = TextAlign.Center,
                ), modifier = Modifier
            )
            Spacer(modifier = Modifier.width(10.dp))
            Icon(
                imageVector = Icons.Outlined.ArrowForwardIos,
                contentDescription = "Notifications Intervals",
                modifier = Modifier.size(SwitchDefaults.IconSize)
            )
        }



    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewProfileScreen() {
    ProfileScreen(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.linearGradient(
                    start = Offset(Float.POSITIVE_INFINITY * 0.4f, 0f),
                    end = Offset(0f, Float.POSITIVE_INFINITY),
                    colors = mutableStateListOf(waterColorMeter.copy(alpha = 0.1f), backgroundColor2)
                )
            ),
        getBack = {},
        getNavigate = {},
        getEmailChange = {},
        getNameChange = {},
        profileData = ProfileData(
            onNameChange = "",
            onEmailError = "",
            onEmailChange = "",
            onEmailCheck = false,
            onNameCheck = false,
            onNameError = "", onNotificationChange = false
        ), getBugReport = {}, getNotificationChange = {}, getNotificationIntervals = {}
    )
}