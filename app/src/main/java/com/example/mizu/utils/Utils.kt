package com.example.mizu.utils

import android.util.Log
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.systemBars

class Utils {

    companion object{
        fun String.capitalizeFirst(): String {
            if (isNotEmpty()) {
                var str =  this.substring(0, 1).uppercase() + this.substring(1)
                Log.d("onNameValue capitalizeFirst",str)
                return str
            }
            Log.d("onNameValue capitalizeFirst",this)

            return this
        }
    }


}