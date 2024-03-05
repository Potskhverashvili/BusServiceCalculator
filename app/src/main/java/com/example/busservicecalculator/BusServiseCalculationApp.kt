package com.example.busservicecalculator

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BusServiceCalculationApp() {
    var amountOfDiagnostic by remember { mutableStateOf("") }
    var amountOfHours by remember { mutableStateOf("") }
    var paymentOfDiagnostics by remember { mutableIntStateOf(1) }
    var paymentOfHours by remember { mutableIntStateOf(1) }

    var expanded by remember { mutableStateOf(false) }
    var selectedBus by remember { mutableStateOf("Select") }

    val listOfBus = listOf("MAN_21", "MAN_A47", "MAN_18CM", "BMC")

    // Create Objects
    val manA21 = ServiceCalculation()
    val manA47 = ServiceCalculation()
    val man18cm = ServiceCalculation()
    val bmc = ServiceCalculation()


    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // ------------------- Input Values -----------------
        Spacer(modifier = Modifier.padding(20.dp))
        Text(text = "Diagnostic")
        OutlinedTextField(
            value = amountOfDiagnostic,
            onValueChange = { amountOfDiagnostic = it },
            singleLine = true,
            placeholder = { Text(text = "Enter Diagnostic") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Text(text = "Hours")
        OutlinedTextField(
            value = amountOfHours,
            onValueChange = { amountOfHours = it },
            singleLine = true,
            placeholder = { Text(text = "Enter Hours") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.padding(18.dp))
        Box {
            Button(onClick = {
                expanded = true
            }) {
                Text(text = selectedBus)
                Icon(Icons.Default.ArrowDropDown, contentDescription = null)
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                listOfBus.forEach {
                    DropdownMenuItem(
                        text = { Text(text = it) },
                        onClick = {
                            selectedBus = it
                            expanded = false

                        }
                    )
                }
            }
        }


        Spacer(modifier = Modifier.padding(30.dp))
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = selectedBus,
                fontSize = 30.sp,
            )

            when (selectedBus) {
                "MAN_21" -> {
                    paymentOfDiagnostics = manA21.calcOfDiagnostic(amountOfDiagnostic.toInt(), DIAGNOSTIC_PRICE_A21)
                    paymentOfHours = manA21.calcOfHours(amountOfHours.toInt(), HOUR_PRICE_A21)
                }
                "MAN_A47" -> {
                    paymentOfDiagnostics = manA47.calcOfDiagnostic(amountOfDiagnostic.toInt(), DIAGNOSTIC_PRICE_A47)
                    paymentOfHours = manA47.calcOfHours(amountOfHours.toInt(), HOUR_PRICE_A47)
                }
                "MAN_18CM" -> {
                    paymentOfDiagnostics = man18cm.calcOfDiagnostic(amountOfDiagnostic.toInt(), DIAGNOSTIC_PRICE_18CM)
                    paymentOfHours = man18cm.calcOfHours(amountOfHours.toInt(), HOUR_PRICE_18CM)
                }
                "BMC" -> {
                    paymentOfDiagnostics = bmc.calcOfDiagnostic(amountOfDiagnostic.toInt(), DIAGNOSTIC_PRICE_BMC)
                    paymentOfHours = bmc.calcOfHours(amountOfHours.toInt(), HOUR_PRICE_BMC)
                }
            }

            Text(
                text = "$paymentOfHours",
                fontSize = 30.sp,
            )

        }
    }
}