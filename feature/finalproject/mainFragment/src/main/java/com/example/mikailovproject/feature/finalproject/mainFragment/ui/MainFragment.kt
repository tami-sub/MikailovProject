package com.example.mikailovproject.feature.finalproject.mainFragment.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.mikailovproject.component.navigation.screen.navigate
import com.example.mikailovproject.feature.finalproject.mainFragment.R
import com.example.mikailovproject.feature.finalproject.mainFragment.databinding.FragmentMainBinding
import com.example.mikailovproject.feature.finalproject.mainFragment.presentation.MainState
import com.example.mikailovproject.feature.finalproject.mainFragment.presentation.MainViewModel
import com.example.mikailovproject.component.navigation.R as NavR
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject


class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    @Inject
    lateinit var viewModel: MainViewModel
    private lateinit var binding: FragmentMainBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
        Log.d("jokahash", "MainFragment ${this.hashCode()}")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentMainBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.secondFragmentButton.setOnClickListener {
            navigate(
                R.id.action_mainFragment_to_secondFragment,
                NavR.id.globalHost,
            )
        }

        binding.loadButton.setOnClickListener {
            viewModel.loadStrings()
        }

        binding.sendNewFileButton.setOnClickListener {
            viewModel.createFile()
        }

        viewModel.state.observe(viewLifecycleOwner) { newState ->
            renderState(newState)
        }

        viewModel.postedFile.observe(viewLifecycleOwner) { it ->
//            when (it) {
//                null -> {}
//                else -> with(binding) {
//                    sentFileInfo.text =
//                        getString(R.string.sent_file_info_text, it)
//                }
//            }
        }
    }

    private fun renderState(state: MainState) = with(binding) {
        when (state) {
            MainState.Loading -> {
                Toast.makeText(requireActivity(), "loading", Toast.LENGTH_SHORT).show()
                remoteText.text = ""
                localText.text = ""
            }

            is MainState.Success -> {
                remoteText.text = state.remoteString
                localText.text = state.localString
            }
        }
    }
}