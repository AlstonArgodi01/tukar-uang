package com.example.tukaruang.data.models

//val pemanggilan mata uang
data class konversirespon(
    val base: String, //nama negara
    val date: String, //tanggal
    val rates: Rates  //jumlah
)