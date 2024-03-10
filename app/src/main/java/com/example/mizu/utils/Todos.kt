package com.example.mizu.utils

data class Todos(var text:String, val onSelected:Boolean, val getSelected:(Boolean)->Unit)
