package com.movingroot.flowassignment.presentation.base

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment : Fragment() {
    var _binding: ViewBinding? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        injectFragmentAndVieModel()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFragment()
    }

    private fun initFragment() {
        bindViewModel()
        setView()
        setObservers()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    abstract fun injectFragmentAndVieModel()

    abstract fun bindViewModel()

    abstract fun setView()

    abstract fun setObservers()
}
