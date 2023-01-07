package com.example.tukaruangcompose.presentasion

import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tukaruangcompose.presentasion.ui.components.Constant
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
                    exchangeLayout()
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
fun currencyInput(
    label : String,
    value : String,
    onChange : (String) -> Unit
){
    TextField(
        value = value,
        onValueChange = onChange,
        label = { Text(text = label)},
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
        )
    )
}
@Composable
fun currencyInput(
    title: String
){
    var expand by remember{ mutableStateOf(false)}
    var value by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .padding(10.dp)
    ){
        Text(
            text = "choose currency",
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = { expand = true })
        )
        Box(){
            DropdownMenu(
                expanded = expand ,
                onDismissRequest = { !expand },
                modifier = Modifier.fillMaxWidth()
            ) {
                Constant.currecnyList.forEach { value ->
                    DropdownMenuItem(
                        onClick = {
                            expand = false
                        },
                    ) {
                        Text(text = value)
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        currencyInput(
            label = title,
            value = value,
            onChange = { value = it }
        )

    }
}



@Composable
fun exchangeLayout(){
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Title(name = "Currency Exchange")
        currencyInput("From")
        Spacer(modifier = Modifier.height(16.dp))
        currencyInput("To")
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun DefaultPreview() {
    TukaruangComposeTheme() {
        exchangeLayout()
    }
}