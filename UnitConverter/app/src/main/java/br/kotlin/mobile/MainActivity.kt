package br.kotlin.mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.kotlin.mobile.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverter()
                }
            }
        }
    }
}

@Composable
fun UnitConverter() {
    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }
    var inputUnity by remember { mutableStateOf("Centimeters") }
    var outputUnity by remember { mutableStateOf("Meters") }
    var iExpended by remember { mutableStateOf(false) }
    var oExpended by remember { mutableStateOf(false) }
    val conversionFactor = remember { mutableDoubleStateOf(0.01) }

    fun convertUnits() {
        //?: - elvis operator
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
        val result = (inputValueDouble * conversionFactor.doubleValue * 100.0).roundToInt() / 100.0
        outputValue = result.toString()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //Here all the UI elements will be stacked below each other
        Text("Unit Converter")
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = inputValue, onValueChange = {

            //Here goes what should happen, when the Value of our OutlinedTextField changes
        }, label = { Text(text = "Enter Value") })
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Box {
                Button(onClick = { iExpended = true }) {
                    Text("Select")
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = iExpended, onDismissRequest = { iExpended = false }) {
                    DropdownMenuItem(
                        text = { Text("Centimeters") },
                        onClick = {
                            iExpended = false
                            inputUnity = "Centimeters"
                            conversionFactor.doubleValue = 0.01
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Meters") },
                        onClick = {
                            iExpended = false
                            inputUnity = "Meters"
                            conversionFactor.doubleValue = 1.0
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Feet") },
                        onClick = {
                            iExpended = false
                            inputUnity = "Feet"
                            conversionFactor.doubleValue = 0.3048
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Millimeters") },
                        onClick = {
                            iExpended = false
                            inputUnity = "Millimeters"
                            conversionFactor.doubleValue = 0.001
                            convertUnits()
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            Box {
                Button(onClick = { oExpended = true }) {
                    Text("Select")
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = oExpended, onDismissRequest = { oExpended = false }) {
                    DropdownMenuItem(
                        text = { Text("Centimeters") },
                        onClick = { }
                    )
                    DropdownMenuItem(
                        text = { Text("Meters") },
                        onClick = { }
                    )
                    DropdownMenuItem(
                        text = { Text("Feet") },
                        onClick = { }
                    )
                    DropdownMenuItem(
                        text = { Text("Millimeters") },
                        onClick = { }
                    )
                }
            }

            //Here all the UI elements will be stacked next each other

        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("Result: ")
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    UnitConverter()
}