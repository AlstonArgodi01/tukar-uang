package com.example.tukaruang.main

import com.example.tukaruang.data.models.konversirespon
import com.example.tukaruang.util.resource

//pemanggilan amta uang
interface mainrepo {
    suspend fun getrates(base: String): resource<konversirespon>
}