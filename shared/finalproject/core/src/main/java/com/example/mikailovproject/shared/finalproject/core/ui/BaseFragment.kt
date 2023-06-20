package com.example.mikailovproject.shared.finalproject.core.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.mikailovproject.shared.finalproject.core.R
import com.google.android.material.snackbar.Snackbar

abstract class BaseFragment<VB : ViewBinding>(
    private val inflate: (LayoutInflater, ViewGroup?, Boolean) -> VB
) : Fragment() {
    private var errorSnackbar: Snackbar? = null
    private var _binding: VB? = null
    protected val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        injectDependencies()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = inflate.invoke(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    protected open fun showErrorSnackbar(message: String, dismiss: Boolean, invoke: () -> Unit) {
        if (!dismiss) {
            errorSnackbar = Snackbar.make(requireView(), message, Snackbar.LENGTH_INDEFINITE)
            errorSnackbar?.setAction(getString(R.string.retry)) {
                invoke()
            }
            errorSnackbar?.show()
        } else {
            errorSnackbar?.dismiss()
        }
    }

    protected abstract fun injectDependencies()
}