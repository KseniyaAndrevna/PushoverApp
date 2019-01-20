package com.kseniyaa.pushoverapp.utils

import android.content.Context
import android.preference.PreferenceManager


fun getFromSP(name: String?, context: Context?): String? {

    val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
    return sharedPreferences.getString(name, "")
}

fun saveToSP(name: String?, token: String?, context: Context) {
    val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
    val editor = sharedPreferences.edit()
    editor.putString(name, token)
    editor.apply()
}

