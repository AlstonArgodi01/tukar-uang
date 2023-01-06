package com.example.tukaruang2.model.currency


import com.google.gson.annotations.SerializedName

data class CurrencyRates(
    @SerializedName("privacy")
    val privacy: String,
    @SerializedName("quotes")
    val CurrencyNote: CurrencyNote,
    @SerializedName("source")
    val source: String,
    @SerializedName("Success")
    val success: Boolean,
    @SerializedName("terms")
    val terms: String,
    @SerializedName("timestamp")
    val timestamp: Int
)