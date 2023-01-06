package com.example.tukaruang2.repo

import com.example.tukaruang2.model.APIattri
import com.example.tukaruang2.util.resources

interface Mainrepo {
    suspend fun getrates(base : String): resources<APIattri>
}