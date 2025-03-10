package com.example.bank_pan_verify

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.bank_pan_verify.screens.BankRegistrationScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BankRegistrationScreen(onFinish = { finish() })
        }
    }
}