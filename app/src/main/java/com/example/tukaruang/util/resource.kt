package com.example.tukaruang.util

sealed class resource<T>(val data: T?, val massage: String?) {
    class success<T>(data: T): resource<T>(data,null)
    class error<T>(message: String): resource<T>(null,message)
}