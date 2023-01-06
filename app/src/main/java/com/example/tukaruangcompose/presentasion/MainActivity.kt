package com.example.tukaruangcompose.presentasion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tukaruangcompose.presentasion.ui.theme.TukaruangComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TukaruangComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Title("Android")
                }
            }
        }
    }
}

@Composable
fun Title(name: String) {
    Text(
        modifier = Modifier.padding(5.dp),
        text = "Currency Exchange",
        fontSize = 25.sp,
        fontStyle = Typography().h1.fontStyle,
        fontWeight = FontWeight.Bold,
    )
}

@Composable
fun optionOne(){
    Row() {
        Text(
            modifier = Modifier.padding(5.dp),
            text = "From",
            fontSize = 18.sp,
            fontStyle = Typography().h3.fontStyle,
            fontWeight = FontWeight.Medium,
        )
    }
}

@Composable
fun exchangeLayout(){
    Column() {
        Title("Android")
        Spacer(modifier = Modifier.height(30.dp))
        optionOne()
    }
}
@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun DefaultPreview() {
    TukaruangComposeTheme {
        exchangeLayout()
    }
}