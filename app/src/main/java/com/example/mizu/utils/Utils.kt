package com.example.mizu.utils

import android.util.Log

class Utils {

    companion object{
        @JvmField
        var ApiKey = "iosdhgaoewrnfnodDFKFS234"

        fun String.capitalizeFirst(): String {
            if (isNotEmpty()) {
                var str =  this.substring(0, 1).uppercase() + this.substring(1)
                Log.d("onNameValue capitalizeFirst",str)
                return str
            }
            Log.d("onNameValue capitalizeFirst",this)

            return this
        }


        fun logIt(tag:String, message:String){
            Log.e(tag,message)
        }


    }


}