package com.bento.a3m1l

import android.content.Context
import android.content.SharedPreferences

class PreferncesHelper {
    private lateinit var sharedPrefernces: SharedPreferences

    fun unit(context: Context){
        sharedPrefernces = context.getSharedPreferences("shared", Context.MODE_PRIVATE)
    }

    var isOnBoardShown: Boolean
        get() = sharedPrefernces.getBoolean("onBoard", false)
        set(value) = sharedPrefernces.edit().putBoolean("onBoard", value).apply()
    var isAnonim: Boolean
        get() = sharedPrefernces.getBoolean("anonim", false)
        set(value) = sharedPrefernces.edit().putBoolean("anonim", value).apply()

}