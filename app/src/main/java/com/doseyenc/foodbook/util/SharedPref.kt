package com.doseyenc.foodbook.util

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

class SharedPref {

    companion object {
        private var sharedPreferences: SharedPreferences? = null

        @Volatile
        private var instance: SharedPref? = null

        private val lock = Any()
        operator fun invoke(context: Context): SharedPref = instance ?: synchronized(lock) {
            instance ?: creatSharedPref(context).also {
                instance = it
            }
        }

        private fun creatSharedPref(context: Context): SharedPref {
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
            return SharedPref()
        }
    }
    fun saveTime(time :Long){
        sharedPreferences?.edit(commit = true)
    }
}