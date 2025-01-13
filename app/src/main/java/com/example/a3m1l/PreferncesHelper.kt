package com.example.a3m1l

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Shader
import kotlinx.coroutines.flow.combine

class PreferncesHelper {
    private lateinit var sharedPrefernces: SharedPreferences

    fun unit(context: Context){
        sharedPrefernces = context.getSharedPreferences("shared", Context.MODE_PRIVATE)
    }
    var text: String?
        get() =sharedPrefernces.getString("text" ,"")
        set(value) = sharedPrefernces.edit().putString("text", value).apply()
}