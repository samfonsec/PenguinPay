package com.samfonsec.penguinpay.ui.main

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE
import com.samfonsec.penguinpay.R
import com.samfonsec.penguinpay.ui.intro.IntroFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_main)
        navigateTo(IntroFragment.newInstance())
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 1)
            super.onBackPressed()
        else
            finish()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == android.R.id.home) {
            onBackPressed()
            true
        } else super.onOptionsItemSelected(item)
    }


    fun navigateTo(
        fragment: Fragment,
        enterAnim: Int = R.anim.slide_in_right,
        exitAnim: Int = R.anim.slide_out_right,
        clearStack: Boolean = false,
    ) {
        supportFragmentManager.run {
            if (clearStack)
                popBackStack(MAIN_BACK_STACK, POP_BACK_STACK_INCLUSIVE)

            beginTransaction()
                .setCustomAnimations(enterAnim, exitAnim, enterAnim, exitAnim)
                .add(R.id.container, fragment)
                .addToBackStack(MAIN_BACK_STACK)
                .commit()
        }
    }

    companion object {
        private const val MAIN_BACK_STACK = "main_back_stack"

        private const val ENTER_ANIM = R.anim.slide_in_right
        private const val EXIT_ANIM = R.anim.slide_out_right
    }
}