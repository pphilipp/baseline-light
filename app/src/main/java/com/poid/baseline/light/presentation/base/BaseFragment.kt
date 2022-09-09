package com.poid.baseline.light.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.WindowInsetsControllerCompat
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = getBinding(inflater, container)
        binding.lifecycleOwner = viewLifecycleOwner

        setAppearanceLightStatusBars(setLightStatusBars())
        return binding.root
    }


    protected abstract fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ViewDataBinding

    protected abstract fun setLightStatusBars(): Boolean

    private fun setAppearanceLightStatusBars(isAppearanceLightStatusBars: Boolean) {
        activity?.window?.let { window ->
            WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars =
                isAppearanceLightStatusBars
        }
    }
}