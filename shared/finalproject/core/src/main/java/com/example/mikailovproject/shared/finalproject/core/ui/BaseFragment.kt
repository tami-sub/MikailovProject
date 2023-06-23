package com.example.mikailovproject.shared.finalproject.core.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.example.mikailovproject.shared.finalproject.core.R
import com.google.android.material.bottomnavigation.BottomNavigationView
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            val navController = findNavController()
            override fun handleOnBackPressed() {
                navController.popBackStack()
                errorSnackbar?.dismiss()
                if (navController.backQueue.size == 0) {
                    requireActivity().finish()
                }
            }
        })
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

    protected open fun showErrorSnackbar(message: String, invoke: () -> Unit) {
        errorSnackbar = Snackbar.make(requireView(), message, Snackbar.LENGTH_INDEFINITE)
        errorSnackbar?.setAction(getString(R.string.retry)) {
            try {
                invoke()
            } catch (e: Exception) {
                dismissErrorSnackBar()
            }
        }
        errorSnackbar?.show()
    }

    protected fun dismissErrorSnackBar() = errorSnackbar?.dismiss()

    protected fun disappearBottomNavigation() {
        requireActivity().findViewById<BottomNavigationView>(R.id.bottom_navigation).visibility =
            View.GONE
    }

    protected fun showBottomNavigation() {
        requireActivity().findViewById<BottomNavigationView>(R.id.bottom_navigation).visibility =
            View.VISIBLE
    }

    protected abstract fun injectDependencies()
}