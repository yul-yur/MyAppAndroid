package com.example.myappandroid

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.myappandroid.ui.theme.MyAppAndroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MyAppAndroidTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    var inputText by rememberSaveable { mutableStateOf("") }
    var errorMessage by rememberSaveable { mutableStateOf<String?>(null) }
    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .imePadding()
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedTextField(
            value = inputText,
            onValueChange = { newText ->
                inputText = newText
                errorMessage = null
            },
            label = { Text("Введите текст") },
            isError = errorMessage != null,
            supportingText = {
                errorMessage?.let { message -> Text(message) }
            },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                val text = inputText.trim()
                if (text.isEmpty()) {
                    errorMessage = "Введите текст"
                } else {
                    errorMessage = null
                    val intent = Intent(context, SecondActivity::class.java)
                    intent.putExtra(SecondActivity.EXTRA_TEXT, text)
                    context.startActivity(intent)
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Открыть вторую Activity")
        }

        Button(
            onClick = {
                val phoneInput = inputText.trim()
                val digits = phoneInput.filter { it in '0'..'9' }
                val hasAllowedCharacters = Regex("\\+?[0-9 ()-]+").matches(phoneInput)

                // Упрощённая проверка обычного номера для учебного задания,
                // а не проверка всех международных форматов и коротких номеров.
                when {
                    phoneInput.isEmpty() ->
                        errorMessage = "Введите номер телефона"

                    !hasAllowedCharacters || digits.length !in 7..15 ->
                        errorMessage = "Введите номер: 7–15 цифр, можно с + в начале"

                    else -> {
                        errorMessage = null
                        val normalizedNumber = if (phoneInput.startsWith("+")) {
                            "+$digits"
                        } else {
                            digits
                        }
                        val dialIntent = Intent(
                            Intent.ACTION_DIAL,
                            Uri.fromParts("tel", normalizedNumber, null)
                        )
                        try {
                            context.startActivity(dialIntent)
                        } catch (_: ActivityNotFoundException) {
                            errorMessage = "На устройстве нет приложения для набора номера"
                        }
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Позвонить другу")
        }

        Button(
            onClick = {
                val text = inputText.trim()
                if (text.isEmpty()) {
                    errorMessage = "Введите текст"
                } else {
                    errorMessage = null
                    val sendIntent = Intent(Intent.ACTION_SEND).apply {
                        type = "text/plain"
                        putExtra(Intent.EXTRA_TEXT, text)
                    }
                    val chooserIntent = Intent.createChooser(sendIntent, "Поделиться текстом")
                    try {
                        context.startActivity(chooserIntent)
                    } catch (_: ActivityNotFoundException) {
                        errorMessage = "Не удалось открыть окно отправки текста"
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Поделиться текстом")
        }
    }
}