package com.kseniyaa.pushoverapp.utils

import android.content.Context
import android.os.Bundle
import android.preference.PreferenceManager
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText


//fun EditText.afterTextChanged(btn_add: Button, et_name: EditText, et_price: EditText) {
//    this.addTextChangedListener(object : TextWatcher {
//        override fun afterTextChanged(s: Editable?) {
//            btn_add.isEnabled = !(et_name.text.isEmpty() || et_price.text.isEmpty())
//        }
//
//        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
//
//        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
//    })
//}
//


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

