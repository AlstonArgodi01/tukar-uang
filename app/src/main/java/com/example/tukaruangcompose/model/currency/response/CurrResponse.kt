package com.example.tukaruangcompose.model.currency.response

import com.google.gson.annotations.SerializedName

data class CurrResponse(
 @SerializedName("privacy")
 val privacy: String,
 @SerializedName("quotes")
 val quotes: List<Quotes>,
 @SerializedName("source")
 val source: String,
 @SerializedName("success")
 val success: Boolean,
 @SerializedName("terms")
 val terms: String,
 @SerializedName("timestamp")
 val timestamp: Int
)