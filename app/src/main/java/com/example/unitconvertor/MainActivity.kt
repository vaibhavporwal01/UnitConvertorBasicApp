package com.example.unitconvertor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
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
import com.example.unitconvertor.ui.theme.UnitConvertorTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UnitConvertorTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    UnitConvertor(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}


@Composable
fun UnitConvertor(modifier: Modifier) {
    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }
    var inputUnit by remember { mutableStateOf("Meters") }
    var outputUnit by remember { mutableStateOf("Meters") }
    var inputExpanded by remember { mutableStateOf(false) }
    var outputExpanded by remember { mutableStateOf(false) }
    val conversionFactor = remember { mutableDoubleStateOf(1.0) }
    val oConversionFactor = remember { mutableDoubleStateOf(1.0) }


    fun convertUnits(){
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
        val result = (inputValueDouble * conversionFactor.doubleValue * 100.0 / oConversionFactor.doubleValue).roundToInt()/100.0
        outputValue = result.toString()
    }


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //Here all the UI elements will be stacked below each other
        Text(text = "Unit Convertor", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.padding(10.dp))
        OutlinedTextField(value = inputValue ,
            onValueChange = {
            inputValue = it
                convertUnits()
            },
            label = { Text("Enter Value") })
        Spacer(modifier = Modifier.padding(10.dp))
        Row {
            //Input Box
            Box{
                //Input Button
                Button(onClick = {inputExpanded = true} ) {
                    Text(inputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = inputExpanded, onDismissRequest = {inputExpanded = false}) {
                    DropdownMenuItem(
                        text = {Text("Centimeters")},
                        onClick = {
                            inputExpanded = false
                            inputUnit = "Centimeters"
                            conversionFactor.doubleValue = 0.01
                            convertUnits()
                        })
                    DropdownMenuItem(
                        text = {Text("Meters")},
                        onClick = {
                            inputExpanded = false
                            inputUnit = "Meters"
                            conversionFactor.doubleValue = 1.0
                            convertUnits()
                        })
                    DropdownMenuItem(
                        text = {Text("Feet")},
                        onClick = {
                            inputExpanded = false
                            inputUnit = "Feet"
                            conversionFactor.doubleValue = 0.3048
                            convertUnits()})
                    DropdownMenuItem(
                        text = {Text("Millimeters")},
                        onClick = {
                            inputExpanded = false
                            inputUnit = "Millimeters"
                            conversionFactor.doubleValue = 0.001
                            convertUnits()})
                }
            }
            Spacer(modifier = Modifier.width(10.dp))
            //Output Box
            Box{
                //Output Button
                Button(onClick = {outputExpanded = true}) {
                    Text(outputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = outputExpanded, onDismissRequest = {outputExpanded = false}) {
                    DropdownMenuItem(
                        text = {Text("Centimeters")},
                        onClick = {
                            outputExpanded = false
                            outputUnit = "Centimeters"
                            oConversionFactor.doubleValue = 0.01
                            convertUnits()
                        })
                    DropdownMenuItem(
                        text = {Text("Meters")},
                        onClick = {
                            inputExpanded = false
                            inputUnit = "Meters"
                            oConversionFactor.doubleValue = 1.00
                            convertUnits()
                        })
                    DropdownMenuItem(
                        text = {Text("Feet")},
                        onClick = {
                            inputExpanded = false
                            inputUnit = "Feet"
                            oConversionFactor.doubleValue = 0.3048
                            convertUnits()
                        })
                    DropdownMenuItem(
                        text = {Text("Millimeters")},
                        onClick = {
                            inputExpanded = false
                            inputUnit = "Millimeters"
                            oConversionFactor.doubleValue = 0.001
                            convertUnits()
                        })
                }
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text("Result : $outputValue $outputUnit",
            style = MaterialTheme.typography.headlineMedium
            )
    }
}


@Preview(showBackground = true)
@Composable
fun UnitConvertorPreview(){
    UnitConvertor(modifier = Modifier)
}


/*val context = LocalContext.current
            //Here all the UI elements will be stacked next to each other
            Button(onClick = { Toast.makeText(context, "Thanks for clicking", Toast.LENGTH_LONG).show()}){
                Text(text = "My Button")
            }

 */