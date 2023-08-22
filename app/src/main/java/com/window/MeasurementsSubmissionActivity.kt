package com.window

import android.content.Context
import android.content.Intent
import android.net.Uri
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import com.window.ui.theme.WindowTheme


class MeasurementsSubmissionActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        setContent {
            WindowTheme {
                MeasurementsSubmissionScreen(LocalContext.current)
            }
        }
    }
}
fun onSubmitClick() {
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
}
fun onSubmitClickUseIntent(context: Context, length: String, width: String, depth: String, quantity: String) {
    val intent = Intent(Intent.ACTION_SENDTO)
    intent.data = Uri.parse("mailto:") // Only email apps should handle this

    intent.putExtra(Intent.EXTRA_SUBJECT, "Window Measurements")
    intent.putExtra(Intent.EXTRA_TEXT, "Length: $length\nWidth: $width\nDepth: $depth\nQuantity: $quantity")

    context.startActivity(intent)
}

@Composable
fun MeasurementsSubmissionScreen(context: Context) {
    var length by remember { mutableStateOf(TextFieldValue()) }
    var width by remember { mutableStateOf(TextFieldValue()) }
    var depth by remember { mutableStateOf(TextFieldValue()) }
    var quantity by remember { mutableStateOf(TextFieldValue()) }

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

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 32.dp, end = 32.dp, bottom = 14.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier.padding(bottom = 6.dp),
                    text = "Length",
                    fontSize = 16.sp
                )

                TextField(
                    value = length,
                    textStyle = TextStyle(fontSize = 16.sp),
                    onValueChange = { length = it },
                    placeholder = {
                        Text(
                            text = "Enter the length of the window in inches",
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

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 32.dp, end = 32.dp, bottom = 14.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier.padding(bottom = 6.dp),
                    text = "Width (in)",
                    fontSize = 16.sp
                )

                TextField(
                    value = width,
                    textStyle = TextStyle(fontSize = 16.sp),
                    onValueChange = { width = it },
                    placeholder = {
                        Text(
                            text = "Enter the width of the window in inches",
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

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 32.dp, end = 32.dp, bottom = 14.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier.padding(bottom = 6.dp),
                    text = "Depth (in)",
                    fontSize = 16.sp
                )

                TextField(
                    value = depth,
                    textStyle = TextStyle(fontSize = 16.sp),
                    onValueChange = { depth = it },
                    placeholder = {
                        Text(
                            text = "Enter the depth of the window in inches",
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

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 32.dp, end = 32.dp, bottom = 14.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier.padding(bottom = 6.dp),
                    text = "Quantity",
                    fontSize = 16.sp
                )

                TextField(
                    value = quantity,
                    textStyle = TextStyle(fontSize = 16.sp),
                    onValueChange = { quantity = it },
                    placeholder = {
                        Text(
                            text = "Enter the quantity of windows.",
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

            DescriptionField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 32.dp, end = 32.dp, bottom = 32.dp),
                title = "Description",
                hint = "Please enter a description of the materials and frame that you would like."
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 40.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = {
                        onSubmitClickUseIntent(
                            context = context,
                            length = length.text,
                            width = width.text,
                            depth = depth.text,
                            quantity = quantity.text)
                    }) {
                    Text(
                        text = "Submit",
                        fontSize = 20.sp
                    )
                }
            }
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

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    WindowTheme {
        MeasurementsSubmissionScreen(LocalContext.current)
    }
}