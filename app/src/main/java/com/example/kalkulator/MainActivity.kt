package com.example.kalkulator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = Color(0xFFEAEAEA)
                ) { innerPadding ->
                    CalculatorScreen(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                            .padding(16.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun CalculatorScreen(modifier: Modifier = Modifier) {
    var firstNumber by rememberSaveable { mutableStateOf("") }
    var secondNumber by rememberSaveable { mutableStateOf("") }
    var result by rememberSaveable { mutableStateOf("N/A") }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Kalkulator",
            style = MaterialTheme.typography.headlineMedium
        )

        OutlinedTextField(
            value = firstNumber,
            onValueChange = { firstNumber = it },
            label = { Text("Pierwsza liczba") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = secondNumber,
            onValueChange = { secondNumber = it },
            label = { Text("Druga liczba") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(onClick = {
                result = calculate(firstNumber, secondNumber, Operation.ADD)
            }, modifier = Modifier.weight(1f)) {
                Text("+")
            }
            Button(onClick = {
                result = calculate(firstNumber, secondNumber, Operation.SUBTRACT)
            }, modifier = Modifier.weight(1f)) {
                Text("-")
            }
            Button(onClick = {
                result = calculate(firstNumber, secondNumber, Operation.MULTIPLY)
            }, modifier = Modifier.weight(1f)) {
                Text("*")
            }
            Button(onClick = {
                result = calculate(firstNumber, secondNumber, Operation.DIVIDE)
            }, modifier = Modifier.weight(1f)) {
                Text("/")
            }
        }

        Text(
            text = result,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}


enum class Operation {
    ADD, SUBTRACT, MULTIPLY, DIVIDE
}

fun calculate(first: String, second: String, operation: Operation): String {
    val a = first.toIntOrNull()
    val b = second.toIntOrNull()

    if (a == null || b == null) {
        return "Wpisz poprawne liczby całkowite"
    }

    return when (operation) {
        Operation.ADD -> "Wynik: ${a + b}"
        Operation.SUBTRACT -> "Wynik: ${a - b}"
        Operation.MULTIPLY -> "Wynik: ${a * b}"
        Operation.DIVIDE -> {
            if (b == 0) {
                "Nie można dzielić przez 0"
            } else {
                "Wynik: ${a / b}"
            }
        }
    }
}