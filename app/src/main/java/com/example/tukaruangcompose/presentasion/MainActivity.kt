package com.example.tukaruangcompose.presentasion

import android.os.Bundle
import android.util.Log
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

fun calculateValue(valueA : Int,valueB : Int):Int{
    return valueA + valueB
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
    title: String,
    getValue : (Int)-> Unit
){
    var expand by remember{ mutableStateOf(false)}
    var numberValue by remember { mutableStateOf("") }
    var selectedCurrency by remember {
        mutableStateOf("choose currency")
    }

    if(numberValue != ""){
        getValue(numberValue.toInt())
    }


    Column(
        modifier = Modifier
            .padding(10.dp)
    ){
        Text(
            text = selectedCurrency,
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
                            selectedCurrency = value
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
            value = numberValue,
            onChange = { numberValue = it }
        )

    }
}

@Composable
fun submitValue(
    valueB: Int,
    valueA: Int,
    result: (Int)->Unit
){

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Button(
            onClick = { result(calculateValue(valueA, valueB)) },
            modifier = Modifier.padding(10.dp)
        ) {
            Text(text = "caculate")
        }
    }
}


@Composable
fun exchangeLayout(){
    var valueA by remember { mutableStateOf(0) }
    var valueB by remember { mutableStateOf(0) }
    var result by remember { mutableStateOf(0) }

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Title(name = "Currency Exchange")
        currencyInput("Value From", getValue = { valueA = it})
        currencyInput("Value To", getValue = { valueB = it})
        submitValue(valueB, valueA, result = { result = it})
        Text(
            modifier = Modifier.padding(5.dp),
            text = result.toString(),
            fontSize = 16.sp,
            fontStyle = Typography().h1.fontStyle,
            fontWeight = FontWeight.Bold,
        )
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