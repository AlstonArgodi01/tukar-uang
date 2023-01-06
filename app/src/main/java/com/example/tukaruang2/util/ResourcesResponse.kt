package com.example.tukaruang2.util

//Utils
sealed class ResourcesResponse<T>(val data: T?, val messeage : String?){
    class Success<T>(data: T): ResourcesResponse<T>(data,null)
    class Error<T>(messeage: String): ResourcesResponse<T>(null,messeage)
}