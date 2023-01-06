package com.example.tukaruang2.model


import com.google.gson.annotations.SerializedName

data class RatesX(
    @SerializedName("privacy")
    val privacy: String,
    @SerializedName("quotes")
    val APIdata: APIdata,
    @SerializedName("source")
    val source: String,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("terms")
    val terms: String,
    @SerializedName("timestamp")
    val timestamp: Int
)