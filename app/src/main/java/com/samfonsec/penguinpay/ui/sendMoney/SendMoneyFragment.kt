package com.samfonsec.penguinpay.ui.sendMoney

import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.ViewModelProvider
import com.samfonsec.penguinpay.R
import com.samfonsec.penguinpay.data.model.Recipient
import com.samfonsec.penguinpay.databinding.FragSendMoneyBinding
import com.samfonsec.penguinpay.ui.BaseFragment
import com.samfonsec.penguinpay.utils.SendMoneyViewModelFactory
import com.samfonsec.penguinpay.utils.extensions.*


class SendMoneyFragment : BaseFragment<FragSendMoneyBinding>() {

    override val contentLayoutId = R.layout.frag_send_money

    private lateinit var viewModel: SendMoneyViewModel

    private val recipient by argument<Recipient>(ARG_RECIPIENT)

    override fun buildUi() {
        viewModel = ViewModelProvider(this, SendMoneyViewModelFactory())[SendMoneyViewModel::class.java]
        subscribeUi()
        with(binding) {
            vm = viewModel
            setActionBar(toolbar)
            toolbar.title = getString(R.string.title_send_money)
            tvRecipientName.text = recipient.name
            tvRecipientCountry.text = recipient.country?.name
            tiSend.setupPrefixLayout()
            btSend.setOnClickListener { onSendClicked() }
            etSend.doAfterTextChanged { setupFieldMaxLength(it.toString()) }
        }
        loadExchangeRates()
    }

    private fun subscribeUi() {
        with(viewModel) {
            country = recipient.country
            onResult.observe(viewLifecycleOwner) { onResult(it) }
            onError.observe(viewLifecycleOwner) { onError() }
            onLoading.observe(viewLifecycleOwner) { onLoading(it) }
        }
    }

    private fun onResult(exchange: Double) {
        with(binding) {
            cvContent.isVisible = true
            tiSend.helperText = getString(R.string.helper_text, exchange)
        }
    }

    private fun onError() {
        with(binding) {
            cvContent.isVisible = false
            gpError.isVisible = true
            btTryAgain.setOnClickListener {
                loadExchangeRates()
                gpError.isVisible = false
            }
        }
    }

    private fun onLoading(isVisible: Boolean) {
        with(binding) {
            if (isVisible) {
                pbLoading.isVisible = true
                cvContent.isVisible = false
                gpError.isVisible = false
            } else {
                pbLoading.isVisible = false
            }
        }
    }

    private fun loadExchangeRates() {
        viewModel.getExchangeRate()
    }

    private fun onSendClicked() {
        binding.etSend.hideSoftKeyboard()
        navigateTo(TransferReceiptFragment.newInstance())
    }

    private fun setupFieldMaxLength(value: String?) {
        binding.etSend.setMaxLength(
            if (viewModel.isLongMaxValueReached())
                value?.length
            else
                null
        )
    }

    companion object {
        private const val ARG_RECIPIENT = "arg_recipient"

        fun newInstance(recipient: Recipient) = SendMoneyFragment().withArgs {
            putParcelable(ARG_RECIPIENT, recipient)
        }
    }
}
