package com.example.tukaruang2.util

sealed class RemoteResponse {
    class Succes(val sukses: String): RemoteResponse() //Succes
    class Failed(val fail: String): RemoteResponse() //Failed
    object Loading: RemoteResponse() //Loading
    object Empty : RemoteResponse()
}