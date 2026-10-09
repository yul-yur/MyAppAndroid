package com.example.myappandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myappandroid.ui.theme.MyAppAndroidTheme

class SecondActivity : ComponentActivity() {
    companion object {
        const val EXTRA_TEXT = "com.example.myappandroid.EXTRA_TEXT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val receivedText = intent.getStringExtra(EXTRA_TEXT)
            ?.takeIf { it.isNotBlank() }
            ?: "Текст не передан"

        setContent {
            MyAppAndroidTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                            .verticalScroll(rememberScrollState())
                            .padding(24.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Text(
                            text = "Полученный текст",
                            style = MaterialTheme.typography.headlineSmall
                        )

                        Text(text = receivedText)

                        Button(onClick = { finish() }) {
                            Text("Назад")
                        }
                    }
                }
            }
        }
    }
}