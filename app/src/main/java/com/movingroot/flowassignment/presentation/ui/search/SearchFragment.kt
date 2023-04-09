package com.movingroot.flowassignment.presentation.ui.search

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import com.movingroot.flowassignment.R
import com.movingroot.flowassignment.databinding.FragmentSearchBinding
import com.movingroot.flowassignment.domain.usecase.search.AddBrowsedRecordUseCase
import com.movingroot.flowassignment.domain.usecase.search.SearchInDetailUseCase
import com.movingroot.flowassignment.domain.usecase.search.SearchUseCaseNetwork
import com.movingroot.flowassignment.presentation.base.BaseFragment
import com.movingroot.flowassignment.presentation.base.ViewModelFactory
import com.movingroot.flowassignment.presentation.ui.MainActivity
import com.movingroot.flowassignment.presentation.ui.MainViewModel
import com.movingroot.flowassignment.presentation.utils.ViewUtil.getInclusiveNavOption
import javax.inject.Inject

class SearchFragment : BaseFragment(), SearchDelegate {
    private val binding: FragmentSearchBinding get() = _binding!! as FragmentSearchBinding
    @Inject lateinit var searchUseCase: SearchUseCaseNetwork
    @Inject lateinit var searchInDetailUseCase: SearchInDetailUseCase
    @Inject lateinit var addBrowsedRecordUseCase: AddBrowsedRecordUseCase
    @Inject lateinit var mainViewModel: MainViewModel
    private lateinit var viewModel: SearchViewModel
    private val adapter: BookAdapter by lazy {
        BookAdapter(this)
    }
    private val scrollPagingListener: OnScrollListener by lazy {
        object : OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!recyclerView.canScrollVertically(1))
                    viewModel.requestNextPageBrowsingResult()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun injectFragmentAndVieModel() {
        (activity as MainActivity).mainComponent.inject(this)
        viewModel = ViewModelFactory(
            searchUseCase, searchInDetailUseCase, addBrowsedRecordUseCase
        ).create(SearchViewModel::class.java)
    }

    override fun bindViewModel() {
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
    }

    override fun setView() {
        binding.booksRecycler.adapter = adapter
    }

    override fun setObservers() {
        viewModel.books.observe(viewLifecycleOwner) { books ->
            adapter.submitList(books)
            (activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?)
                ?.hideSoftInputFromWindow(binding.inputField.windowToken, 0)
        }
        viewModel.checkRecentBrowsedRecord.observe(viewLifecycleOwner) { check ->
            if (check) {
                navToRecords()
                viewModel.initializeCheckingRecord()
            }
        }
        viewModel.errorMsg.observe(viewLifecycleOwner) {
            makeAlertDialog(it[0], it[1])
        }
        mainViewModel.selectedKeyword.observe(viewLifecycleOwner) { keyword ->
            viewModel.browse(keyword)
        }
    }

    private fun makeAlertDialog(title: Int, message: Int) {
        AlertDialog.Builder(requireContext())
            .run {
                setTitle(getString(title))
                setMessage(getString(message))
                setPositiveButton(getString(R.string.confirm)) { dialog, _ ->
                    dialog.dismiss()
                }
                create()
                show()
            }
    }

    private fun navToRecords() {
        val navOption = getInclusiveNavOption(R.id.recentlyBrowsedFragment)
        findNavController().navigate(
            SearchFragmentDirections.actionSearchFragmentToRecentlyBrowsedFragment(),
            navOption
        )
    }

    override fun onResume() {
        super.onResume()
        binding.booksRecycler.addOnScrollListener(scrollPagingListener)
    }

    override fun onStop() {
        super.onStop()
        binding.booksRecycler.removeOnScrollListener(scrollPagingListener)
    }

    override fun navWithLink(link: String) {
        val navOption = getInclusiveNavOption(R.id.detailFragment)
        findNavController().navigate(
            SearchFragmentDirections.actionSearchFragmentToDetailFragment(link),
            navOption
        )
    }
}
