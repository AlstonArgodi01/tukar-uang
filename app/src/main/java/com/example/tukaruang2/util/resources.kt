package com.example.tukaruang2.util

//Utils
sealed class resources<T>(val data: T?, val messeage : String?){
    class success<T>(data: T): resources<T>(data,null)
    class error<T>(messeage: String): resources<T>(null,messeage)
}