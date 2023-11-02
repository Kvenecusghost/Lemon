package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LemonadeTheme {
                        Lemon(modifier = Modifier)
                    }
                }
            }
        }
    }
}

@Composable
fun Lemon(modifier: Modifier = Modifier) {
    var state by remember {
        mutableStateOf(1)
    }
    var numberOfClicks by remember {
        mutableStateOf((1..6).random())
    }
    val (imageId, description) = when (state) {
        1 -> R.drawable.lemon_tree to "Tap the lemon tree"
        2 -> R.drawable.lemon_squeeze to "Keep tapping the lemon to squeeze it"
        3 -> R.drawable.lemon_drink to "Tap the lemonade to drink it"
        else -> R.drawable.lemon_restart to "Tap the empty glass to restart"
    }

    Column (
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Image(painter = painterResource(id = imageId), contentDescription = description)
        Button(onClick = {
            when (state) {
                1 -> {
                    state = 2
                    numberOfClicks = (1..6).random()
                }
                2 -> if (numberOfClicks != 0) numberOfClicks-- else state = 3
                3 -> state = 4
                else -> state = 1
            }
        }) {
            Text(text = description)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LemonadeTheme {
        Lemon()
    }
}