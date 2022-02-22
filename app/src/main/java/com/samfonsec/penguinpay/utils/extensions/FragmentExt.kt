package com.samfonsec.penguinpay.utils.extensions

import android.os.Bundle
import android.view.View
import androidx.annotation.ColorRes
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.Fragment
import com.samfonsec.penguinpay.R

inline fun <reified T> Fragment.argument(key: String): Lazy<T> = lazy {
    val value = arguments?.get(key)
    if (value is T)
        value
    else
        throw IllegalArgumentException("Couldn't find extra with ky $key.")
}

inline fun <T : Fragment> T.withArgs(argsBuilder: Bundle. () -> Unit): T = this.apply {
    arguments = Bundle().apply(argsBuilder)
}

fun Fragment.setStatusBarColor(@ColorRes colorRes: Int, isLight: Boolean = false) {
    activity?.run {
        window?.statusBarColor = getColor(colorRes)
        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = isLight
    }
}