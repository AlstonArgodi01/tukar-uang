package com.example.tukaruang2.model.currency

data class CurrencyAttribute(
    val privacy: String,
    val terms: String,
    val success: Boolean,
    val CurrencyNote: CurrencyNote,
    val source: String,
    val timestamp: String
)