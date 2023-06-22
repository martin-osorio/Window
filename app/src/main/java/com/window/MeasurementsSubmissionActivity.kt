package com.window

import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.window.ui.theme.WindowTheme

class MeasurementsSubmissionActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        setContent {
            WindowTheme {
                MeasurementsSubmissionScreen()
            }
        }
    }
}

@Composable
fun MeasurementsSubmissionScreen() {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        color = MaterialTheme.colors.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            ScreenTitle(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 32.dp, end = 32.dp, top = 32.dp, bottom = 16.dp),
                title = "Window"
            )

            ScreenSubtitle(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 32.dp, end = 32.dp, bottom = 26.dp),
                subtitle = "To request a quote for a window, please enter the measurements of your window in inches, the quantity of identical windows you'd like, and a description of the materials and frame that you would like."
            )

            MeasurementField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 32.dp, end = 32.dp, bottom = 12.dp),
                title = "Length (in)",
                hint = "Enter the length of the window in inches"
            )

            MeasurementField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 32.dp, end = 32.dp, bottom = 14.dp),
                title = "Height (in)",
                hint = "Enter the height of the window in inches"
            )

            MeasurementField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 32.dp, end = 32.dp, bottom = 14.dp),
                title = "Depth (in)",
                hint = "Enter the depth of the window in inches"
            )

            MeasurementField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 32.dp, end = 32.dp, bottom = 14.dp),
                title = "Quantity",
                hint = "Enter the quantity of windows."
            )

            DescriptionField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 32.dp, end = 32.dp, bottom = 32.dp),
                title = "Description",
                hint = "Please enter a description of the materials and frame that you would like."
            )

            SubmitMeasurementsButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 40.dp)
            )
        }
    }
}

@Composable
fun ScreenTitle(modifier: Modifier, title: String) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            fontSize = 28.sp
        )
    }
}

@Composable
fun ScreenSubtitle(modifier: Modifier, subtitle: String) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = subtitle,
            fontSize = 14.sp
        )
    }
}

@Composable
fun MeasurementField(modifier: Modifier, title: String, hint: String) {
    val input = remember { mutableStateOf(TextFieldValue()) }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(bottom = 6.dp),
            text = title,
            fontSize = 16.sp
        )

        TextField(
            value = input.value,
            textStyle = TextStyle(fontSize = 16.sp),
            onValueChange = { input.value = it },
            placeholder = {
                Text(
                    text = hint,
                    fontSize = 14.sp
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp, bottom = 6.dp),
            maxLines = 1,
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        )
    }
}

@Composable
fun DescriptionField(modifier: Modifier, title: String, hint: String) {
    val input = remember { mutableStateOf(TextFieldValue()) }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(bottom = 6.dp),
            text = title,
            fontSize = 16.sp
        )

        TextField(
            value = input.value,
            onValueChange = { input.value = it },
            placeholder = {
                Text(
                    text = hint,
                    fontSize = 14.sp
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp, bottom = 6.dp),
            singleLine = false,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        )
    }
}

@Composable
fun SubmitMeasurementsButton(modifier: Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                try {
                    val sender = GMailSender("username@gmail.com", "password")
                    sender.sendMail(
                        "This is Subject",
                        "This is Body",
                        "user@gmail.com",
                        "user@yahoo.com"
                    )
                } catch (e: Exception) {
                    Log.e("SendMail", e.message, e)
                }
            }) {
            Text(
                text = "Submit",
                fontSize = 20.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    WindowTheme {
        MeasurementsSubmissionScreen()
    }
}