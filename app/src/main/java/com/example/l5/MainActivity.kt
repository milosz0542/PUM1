package com.example.l5

import android.os.Bundle
//import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.l5.ui.theme.L5Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            L5Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CounterExample()
                }
            }
        }
    }
}

@Composable
fun CounterExample(){
    var counter by rememberSaveable { mutableIntStateOf(0) }
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ){
        Spacer(modifier = Modifier.weight(0.3f))
        Text(
            text = counter.toString(),
            fontSize = 250.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(1f)
        )

        Button(
            modifier = Modifier.fillMaxWidth(),
            shape = RectangleShape,
            onClick = { counter = 0 }
        ) {
            Text(text = "RESET")
        }

        Row(verticalAlignment = Alignment.Bottom,
            modifier = Modifier.fillMaxWidth()
        ){

            Button(
                modifier = Modifier.weight(1f),
                shape = RectangleShape,
                onClick = { counter++ }
            ) {
                Text(text = "Count UP")
            }

            Button(
                modifier = Modifier.weight(1f),
                shape = RectangleShape,
                onClick = { counter-- }
            ) {
                Text(text = "Count DOWN")
            }
        }
    }
}