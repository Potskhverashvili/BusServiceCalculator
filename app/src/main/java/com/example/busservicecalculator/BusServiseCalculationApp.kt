package com.example.busservicecalculator

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
    // User input fields
    var userInput1 by remember { mutableStateOf("") }
    var userInput2 by remember { mutableStateOf("") }

    // to store amount of diagnostic and hour from userInput1 & userInput2
    var diagnosticAmount by remember { mutableIntStateOf(0) }
    var hourAmount by remember { mutableIntStateOf(0) }

    // Calculated payment amounts
    var diagnosticPayment by remember { mutableIntStateOf(0) }
    var hourPayment by remember { mutableIntStateOf(0) }

    // List of supported bus types
    val listOfBus = listOf("MAN_21", "MAN_A47", "MAN_18CM", "BMC")

    // Currently selected bus type
    var selectedBus by remember { mutableStateOf("Select") }

    // Dropdown menu state
    var isDropdownExpanded by remember { mutableStateOf(false) }

    // Instance of the calculator class
    val calculator = BusServiceCalculator()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // ------------------- Input Values -----------------
        Spacer(modifier = Modifier.padding(20.dp))

        Text(text = "Diagnostic")
        OutlinedTextField(
            value = userInput1,
            onValueChange = { userInput1 = it },
            singleLine = true,
            placeholder = { Text(text = "Enter Diagnostic") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),

            )

        Text(text = "Hours")
        OutlinedTextField(
            value = userInput2,
            onValueChange = { userInput2 = it },
            singleLine = true,
            placeholder = { Text(text = "Enter Hours") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.padding(18.dp))
        Box {
            Button(onClick = {
                isDropdownExpanded = true
            }) {
                Text(text = selectedBus)
                Icon(Icons.Default.ArrowDropDown, contentDescription = null)
            }

            DropdownMenu(
                expanded = isDropdownExpanded,
                onDismissRequest = { isDropdownExpanded = false }
            ) {
                listOfBus.forEach {
                    DropdownMenuItem(
                        text = { Text(text = it) },
                        onClick = {
                            selectedBus = it
                            isDropdownExpanded = false
                        }
                    )
                }
            }
        }

        // ----------------- Calculate diagnostic and hourly payments -----------------------
        Spacer(modifier = Modifier.padding(30.dp))
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = selectedBus,
                fontSize = 30.sp,
            )

            // -------------- Handle empty or invalid input ----------------
            diagnosticAmount = userInput1.toIntOrNull() ?: 1
            hourAmount = userInput2.toIntOrNull() ?: 1

            // ------------------ Calculate Result -------------------------
            diagnosticPayment = calculator.calculateDiagnosticPayment(diagnosticAmount, selectedBus)
            hourPayment = calculator.calculateHourlyPayment(hourAmount, selectedBus)

            // ------------------- Print Result ----------------------------
            Text(
                text = "$diagnosticAmount Diagnostic = $diagnosticPayment Euro",
                fontSize = 20.sp,
            )

            Text(
                text = "\n$hourAmount Hours = $hourPayment Euro",
                fontSize = 20.sp,
            )
        }
    }
}