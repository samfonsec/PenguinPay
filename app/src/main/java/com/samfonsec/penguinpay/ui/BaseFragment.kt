package com.samfonsec.penguinpay.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.samfonsec.penguinpay.R
import com.samfonsec.penguinpay.ui.main.MainActivity

abstract class BaseFragment<B : ViewDataBinding> : Fragment() {

    protected lateinit var binding: B

    protected abstract val contentLayoutId: Int

    abstract fun buildUi()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(inflater, contentLayoutId, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buildUi()
    }

    fun setActionBar(toolbar: Toolbar) {
        (activity as MainActivity).run {
            setSupportActionBar(toolbar)
            supportActionBar?.let {
                it.setDisplayShowTitleEnabled(false)
                it.setDisplayHomeAsUpEnabled(true)
            }
        }
    }

    fun navigateTo(
        fragment: Fragment,
        enterAnim: Int = R.anim.slide_in_right,
        exitAnim: Int = R.anim.slide_out_right,
        clearStack: Boolean = false
    ) {
        (activity as MainActivity).navigateTo(fragment, enterAnim, exitAnim, clearStack)
    }
}