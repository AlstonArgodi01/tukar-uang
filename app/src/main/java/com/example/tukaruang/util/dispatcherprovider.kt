package com.example.tukaruang.util

import kotlinx.coroutines.CoroutineDispatcher

interface dispatcherprovider {
    val main: CoroutineDispatcher
    val io: CoroutineDispatcher
    val default: CoroutineDispatcher
    val unconfined: CoroutineDispatcher
}