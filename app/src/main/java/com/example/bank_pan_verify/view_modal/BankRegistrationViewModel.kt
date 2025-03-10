package com.example.bank_pan_verify.view_modal

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.regex.Pattern

class BankRegistrationViewModel : ViewModel() {

    private val _panNumber = MutableStateFlow("")
    val panNumber: StateFlow<String> = _panNumber

    private val _day = MutableStateFlow("")
    val day: StateFlow<String> = _day

    private val _month = MutableStateFlow("")
    val month: StateFlow<String> = _month

    private val _year = MutableStateFlow("")
    val year: StateFlow<String> = _year

    private val _isFormValid = MutableStateFlow(false)
    val isFormValid: StateFlow<Boolean> = _isFormValid

    // PAN Validation Regex (Indian PAN format: 5 letters, 4 digits, 1 letter)
    private val panPattern = Pattern.compile("[A-Z]{5}[0-9]{4}[A-Z]")

    fun onPanNumberChanged(pan: String) {
        _panNumber.value = pan.uppercase()
        validateForm()
    }

    fun onDayChanged(day: String) {
        _day.value = day
        validateForm()
    }

    fun onMonthChanged(month: String) {
        _month.value = month
        validateForm()
    }

    fun onYearChanged(year: String) {
        _year.value = year
        validateForm()
    }

    private fun validateForm() {
        _isFormValid.value = isValidPan(_panNumber.value) && isValidDate(_day.value, _month.value, _year.value)
    }

    private fun isValidPan(pan: String): Boolean {
        return panPattern.matcher(pan).matches()
    }

    private fun isValidDate(day: String, month: String, year: String): Boolean {
        if (day.isEmpty() || month.isEmpty() || year.isEmpty()) return false

        return try {
            val dayInt = day.toInt()
            val monthInt = month.toInt()
            val yearInt = year.toInt()

            if (yearInt < 1900 || yearInt > 2100) return false
            if (monthInt !in 1..12) return false

            val maxDays = when (monthInt) {
                1, 3, 5, 7, 8, 10, 12 -> 31
                4, 6, 9, 11 -> 30
                2 -> if (yearInt % 4 == 0 && (yearInt % 100 != 0 || yearInt % 400 == 0)) 29 else 28
                else -> return false
            }
            dayInt in 1..maxDays
        } catch (e: NumberFormatException) {
            false
        }
    }
}