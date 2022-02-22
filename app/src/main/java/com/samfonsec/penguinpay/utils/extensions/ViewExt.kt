package com.samfonsec.penguinpay.utils.extensions

import android.content.Context
import android.text.InputFilter
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.core.view.updateLayoutParams
import com.google.android.material.textfield.TextInputLayout

fun View.hideSoftKeyboard() {
    clearFocus()
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}

fun TextInputLayout.setupPrefixLayout() = with(prefixTextView) {
    updateLayoutParams { height = ViewGroup.LayoutParams.MATCH_PARENT }
    gravity = Gravity.CENTER
}

fun TextView.setMaxLength(max: Int?) {
    max?.let {
        filters = arrayOf(InputFilter.LengthFilter(max))
    } ?: run {
        filters = arrayOf()
    }
}
