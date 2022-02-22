package com.samfonsec.penguinpay.ui.recipient


import android.widget.ArrayAdapter
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.ViewModelProvider
import com.samfonsec.penguinpay.R
import com.samfonsec.penguinpay.databinding.FragRecipientDataBinding
import com.samfonsec.penguinpay.ui.BaseFragment
import com.samfonsec.penguinpay.ui.sendMoney.SendMoneyFragment
import com.samfonsec.penguinpay.utils.Country
import com.samfonsec.penguinpay.utils.extensions.hideSoftKeyboard
import com.samfonsec.penguinpay.utils.extensions.setMaxLength
import com.samfonsec.penguinpay.utils.extensions.setupPrefixLayout


class RecipientFragment : BaseFragment<FragRecipientDataBinding>() {

    override val contentLayoutId = R.layout.frag_recipient_data

    private lateinit var viewModel: RecipientViewModel

    override fun buildUi() {
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[RecipientViewModel::class.java]

        setupCountriesList()
        with(binding) {
            vm = viewModel
            setActionBar(toolbar)
            toolbar.title = getString(R.string.title_recipient)
            tiPhone.setupPrefixLayout()
            etCountry.doAfterTextChanged { onCountrySelected(it.toString()) }
            btContinue.setOnClickListener { onContinueClicked() }
        }
    }

    private fun setupCountriesList() {
        context?.run {
            ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, Country.names()).let {
                binding.etCountry.setAdapter(it)
            }
        }
    }

    private fun onCountrySelected(country: String) {
        Country.getByName(country)?.let {
            with(binding) {
                tiPhone.isEnabled = true
                tiPhone.prefixText = it.phonePrefix
                tiPhone.isExpandedHintEnabled = false
                tiPhone.requestFocus()
                etPhone.text = null
                etPhone.setMaxLength(it.phoneLength)
                etPhone.doAfterTextChanged { phone ->
                    if (phone?.length == it.phoneLength)
                        etPhone.hideSoftKeyboard()
                }
            }
        }
    }

    private fun onContinueClicked() {
        navigateTo(SendMoneyFragment.newInstance(viewModel.getRecipientData()))
    }

    companion object {
        fun newInstance() = RecipientFragment()
    }
}