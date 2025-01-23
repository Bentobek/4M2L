package com.example.a3m1l

import android.app.Application
import androidx.room.Room
import com.example.a3m1l.data.db.AppDataBase

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        getInstatnce()
        val shadePrefernces = PreferncesHelper()
        shadePrefernces.unit(this)

    }
    private fun getInstatnce(): AppDataBase? {
        if (appDataBase == null) {
            appDataBase = applicationContext?.let { context ->
                Room.databaseBuilder(
                    context,
                    AppDataBase::class.java,
                    "note.database"
                ).fallbackToDestructiveMigration().allowMainThreadQueries().build()
            }
        }
        return appDataBase
    }

    companion object{
        var appDataBase: AppDataBase? = null
    }
}