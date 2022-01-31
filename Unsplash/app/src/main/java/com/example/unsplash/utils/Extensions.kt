package com.example.unsplash.utils

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

//Is Json??
fun String?.isJsonObject() : Boolean = this?.startsWith("{") == true && this.endsWith("}")

//Is Json Array??
fun String?.isJsonArray() : Boolean = this?.startsWith("[") == true && this.endsWith("]")

fun EditText.onMyTextChanged(completion : (Editable?) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun afterTextChanged(p0: Editable?) {
            completion(p0)
        }
    })
}