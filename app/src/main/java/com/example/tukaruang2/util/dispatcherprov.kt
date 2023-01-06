package com.example.tukaruang2.util

import kotlinx.coroutines.CoroutineDispatcher

//fungsi pemangilan dispatcher
interface dispatcherprov {
    val main : CoroutineDispatcher
    val io : CoroutineDispatcher
    val default : CoroutineDispatcher
    val unconfied : CoroutineDispatcher
}