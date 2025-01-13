package com.example.a3m1l

import android.app.Application

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        val shadePrefernces = PreferncesHelper()
        shadePrefernces.unit(this)
    }
}