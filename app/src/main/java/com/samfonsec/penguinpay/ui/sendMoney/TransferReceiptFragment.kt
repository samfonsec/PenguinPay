package com.samfonsec.penguinpay.ui.sendMoney

import androidx.activity.OnBackPressedCallback
import com.samfonsec.penguinpay.R
import com.samfonsec.penguinpay.databinding.FragTransferReceiptBinding
import com.samfonsec.penguinpay.ui.BaseFragment
import com.samfonsec.penguinpay.ui.intro.IntroFragment
import com.samfonsec.penguinpay.utils.extensions.setStatusBarColor

class TransferReceiptFragment : BaseFragment<FragTransferReceiptBinding>() {

    override val contentLayoutId = R.layout.frag_transfer_receipt

    override fun buildUi() {
        setStatusBarColor(R.color.primaryColor)
        binding.btFinish.setOnClickListener { backToBeginning() }
        setupOnBackPressed()
    }

    private fun setupOnBackPressed() {
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                backToBeginning()
            }
        })
    }

    private fun backToBeginning() {
        navigateTo(
            IntroFragment.newInstance(),
            android.R.anim.fade_in,
            android.R.anim.fade_out,
            clearStack = true
        )
    }

    companion object {
        fun newInstance() = TransferReceiptFragment()
    }
}