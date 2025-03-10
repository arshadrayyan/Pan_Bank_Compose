package com.example.bank_pan_verify.screens

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bank_pan_verify.view_modal.BankRegistrationViewModel

@Composable
fun BankRegistrationScreen(viewModel: BankRegistrationViewModel = viewModel(), onFinish: () -> Unit) {
    val context = LocalContext.current

    val panNumber by viewModel.panNumber.collectAsState()
    val day by viewModel.day.collectAsState()
    val month by viewModel.month.collectAsState()
    val year by viewModel.year.collectAsState()
    val isFormValid by viewModel.isFormValid.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize().padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "First of the few steps to set you up with a Bank Account", style = MaterialTheme.typography.headlineLarge)

        Spacer(modifier = Modifier.height(16.dp))

        // PAN Number Input
        OutlinedTextField(
            value = panNumber,
            onValueChange = viewModel::onPanNumberChanged,
            label = { Text("PAN Number") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Birthdate Input Fields
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            OutlinedTextField(
                value = day,
                onValueChange = viewModel::onDayChanged,
                label = { Text("Day") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            OutlinedTextField(
                value = month,
                onValueChange = viewModel::onMonthChanged,
                label = { Text("Month") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            OutlinedTextField(
                value = year,
                onValueChange = viewModel::onYearChanged,
                label = { Text("Year") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                modifier = Modifier.weight(1.5f)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Providing PAN & Date of Birth helps us find and fetch your KYC from a central registry by the Government of India.",
            style = MaterialTheme.typography.bodySmall
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Next Button
        Button(
            onClick = {
                Toast.makeText(context, "Details submitted successfully", Toast.LENGTH_SHORT).show()
                onFinish()
            },
            enabled = isFormValid,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "NEXT")
        }

        Spacer(modifier = Modifier.height(8.dp))

        // "I don't have a PAN" Button
        TextButton(
            onClick = onFinish,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "I don’t have a PAN")
        }
    }

    // Handle Back Press
    BackHandler {
        onFinish()
    }
}