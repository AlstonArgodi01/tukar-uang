package com.example.tukaruangcompose.presentasion

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tukaruangcompose.presentasion.model.MainViewModel
import com.example.tukaruangcompose.presentasion.model.ViewModelFactory
import com.example.tukaruangcompose.presentasion.ui.components.Constant
import com.example.tukaruangcompose.presentasion.ui.helpers.CalcualteValue
import com.example.tukaruangcompose.presentasion.ui.theme.TukaruangComposeTheme
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TukaruangComposeTheme {
                val viewModel : MainViewModel by viewModels{
                    ViewModelFactory.getInstance(this)
                }

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ExchangeLayout(
                        viewModel
                    )
                }
            }
        }
    }
}

suspend fun calculateCurrency(
    from : String,
    valueA : Int,
    to : String,
    valueB : Int,
    context : Context,
    viewModel: MainViewModel
){
    viewModel.conversion(
        from = from,
        amountFrom = valueA,
        to = to,
        amountTo = valueB
    )
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
fun CurrencyInputTextField(
    label : String,
    value : String,
    onChange : (String) -> Unit
){
    TextField(
        value = value,
        onValueChange = onChange,
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
        )
    )
}
@Composable
fun InputLayout(
    title: String,
    getValue : (Int)-> Unit,
    getCurrency : (String) -> Unit,
    type : String,
){
    var expand by remember{
        mutableStateOf(false)
    }
    var numberValue by remember {
        mutableStateOf("")
    }
    var selectedCurrency by remember {
        mutableStateOf("choose currency")
    }

    if(numberValue != ""){
        getValue(numberValue.toInt())
    }
    if (selectedCurrency.isNotEmpty()){
        getCurrency(selectedCurrency)
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
                        Text(
                            text = "$type $value"
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        CurrencyInputTextField(
            label = title,
            value = numberValue,
            onChange = { numberValue = it }
        )

    }
}

@Composable
fun SubmitValue(
    from : String,
    to : String,
    valueB: Int,
    valueA: Int,
    result: (Int)->Unit,
    viewModel: MainViewModel,
){
    val coroutine = rememberCoroutineScope()
    val lifeyclerOwner = rememberUpdatedState(newValue = LocalLifecycleOwner.current)
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Button(
            onClick = {
                coroutine.launch {
                    viewModel.conversion(
                        from = from,
                        amountFrom = valueA,
                        to = to,
                        amountTo = valueB
                    ).observe(lifeyclerOwner.value){
                        Log.d("result",it.toString())
                    }
                }
            },
            modifier = Modifier.padding(10.dp)
        ) {
            Text(text = "caculate")
        }
    }

}

@Composable
fun ExchangeLayout(
    viewModel: MainViewModel
){
    var valueA by remember { mutableStateOf(0) }
    var valueB by remember { mutableStateOf(0) }
    var currencyA by remember { mutableStateOf("") }
    var currencyB by remember { mutableStateOf("") }

    var result by remember { mutableStateOf(0) }

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Title(name = "Currency Exchange")
        InputLayout(
            "Value From",
            getValue = { valueA = it},
            getCurrency = { currencyA = it},
            type = "from"
        )
        InputLayout(
            "Value To",
            getValue = { valueB = it},
            getCurrency = { currencyB = it},
            type = "to"
        )
        SubmitValue(
            from = currencyA,
            to = currencyB,
            valueB = valueB,
            valueA = valueA,
            result = { result = it},
            viewModel = viewModel
        )
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
//        ExchangeLayout()
    }
}