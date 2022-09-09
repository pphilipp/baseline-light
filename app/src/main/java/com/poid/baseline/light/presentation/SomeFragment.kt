package com.poid.baseline.light.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.poid.baseline.light.R
import com.poid.baseline.light.databinding.FragmentSomeBinding
import com.poid.baseline.light.presentation.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class SomeFragment: BaseFragment() {
    private lateinit var binding: FragmentSomeBinding
    private val viewModel by viewModel<SomeViewModel>()

    override fun getBinding(inflater: LayoutInflater, container: ViewGroup?): ViewDataBinding {
        binding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.fragment_some,
            container,
            false
        )

        return binding
    }

    override fun setLightStatusBars(): Boolean = true
}