package com.movingroot.flowassignment.presentation.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.movingroot.flowassignment.databinding.FragmentDetailBinding
import com.movingroot.flowassignment.presentation.base.BaseFragment
import com.movingroot.flowassignment.presentation.base.ViewModelFactory
import com.movingroot.flowassignment.presentation.ui.MainActivity
import com.movingroot.flowassignment.presentation.utils.WebViewUtil.setBasics
import com.movingroot.flowassignment.presentation.utils.WebViewUtil.setClients

class DetailFragment : BaseFragment() {
    private val binding: FragmentDetailBinding get() = _binding!! as FragmentDetailBinding
    private lateinit var viewModel: DetailViewModel
    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun injectFragmentAndVieModel() {
        (activity as MainActivity).mainComponent.inject(this)
        viewModel = ViewModelFactory().create(DetailViewModel::class.java)
    }

    override fun bindViewModel() {
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
    }

    override fun setView() {
        setWebView()
    }

    override fun setObservers() {
        viewModel.toGoBack.observe(viewLifecycleOwner) { finish ->
            if (finish)
                findNavController().popBackStack()
        }
    }

    private fun setWebView() {
        binding.webView.run {
            settings.setBasics()
            setClients(
                chromeClient = getChromeClient(),
                webViewClient = getCustomWebViewClient()
            )
        }
    }

    private fun getChromeClient(): ChromeClient = ChromeClient(
        pageStartCallback = {
            viewModel.startLoading()
        },
        pageFinishCallback = {
            viewModel.finishLoading()
        },
        closeWindowCallback = {
            binding.webView.removeView(it)
            findNavController().popBackStack()
        }
    )

    private fun getCustomWebViewClient(): CustomLoadWebViewClient {
        return CustomLoadWebViewClient(
            pageStartCallback = {
                viewModel.startLoading()
            },
            pageFinishCallback = {
                viewModel.finishLoading()
            }
        )
    }

    override fun onResume() {
        super.onResume()
        loadUrl()
    }

    override fun onStop() {
        super.onStop()
        viewModel.finishLoading()
    }

    private fun loadUrl() {
        binding.webView.loadUrl(args.link)
    }
}
