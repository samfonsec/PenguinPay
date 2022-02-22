package com.samfonsec.penguinpay.ui.intro

import com.samfonsec.penguinpay.R
import com.samfonsec.penguinpay.databinding.FragIntroBinding
import com.samfonsec.penguinpay.ui.BaseFragment
import com.samfonsec.penguinpay.ui.recipient.RecipientFragment
import com.samfonsec.penguinpay.utils.extensions.setStatusBarColor

class IntroFragment : BaseFragment<FragIntroBinding>() {

    override val contentLayoutId = R.layout.frag_intro

    override fun buildUi() {
        setStatusBarColor(R.color.windowBackgroundColor, isLight = true)
        binding.btStart.setOnClickListener {
            navigateTo(RecipientFragment.newInstance())
        }
    }

    companion object {
        fun newInstance() = IntroFragment()
    }
}