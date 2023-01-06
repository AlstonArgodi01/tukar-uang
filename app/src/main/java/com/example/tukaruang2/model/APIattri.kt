package com.example.tukaruang2.model

data class APIattri(
    //kebijakan
    val privacy: String,
    val terms: String,
    val success: Boolean,

    val APIdata: APIdata,//data dari api
    val source: String,//sumber konversi
    val timestamp: String //tanggal
)