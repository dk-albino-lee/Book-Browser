package com.movingroot.flowassignment.presentation.ui.recently_browsed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.movingroot.flowassignment.data.model.BrowsedRecordEntity
import com.movingroot.flowassignment.databinding.FragmentRecentlyBrowsedBinding
import com.movingroot.flowassignment.domain.usecase.recently_browsed.ClearAllBrowsedRecordsUseCase
import com.movingroot.flowassignment.domain.usecase.recently_browsed.DeleteBrowsedRecordUseCase
import com.movingroot.flowassignment.domain.usecase.recently_browsed.GetBrowsedRecordsUseCase
import com.movingroot.flowassignment.presentation.base.BaseFragment
import com.movingroot.flowassignment.presentation.base.ViewModelFactory
import com.movingroot.flowassignment.presentation.ui.MainActivity
import com.movingroot.flowassignment.presentation.ui.MainViewModel
import javax.inject.Inject

class RecentlyBrowsedFragment : BaseFragment(), RecentlyBrowsedDelegate {
    private val binding: FragmentRecentlyBrowsedBinding get() = _binding!! as FragmentRecentlyBrowsedBinding
    @Inject lateinit var getRecordsUseCase: GetBrowsedRecordsUseCase
    @Inject lateinit var deleteRecordUseCase: DeleteBrowsedRecordUseCase
    @Inject lateinit var clearAllRecordsUseCase: ClearAllBrowsedRecordsUseCase
    @Inject lateinit var mainViewModel: MainViewModel
    private lateinit var viewModel: RecentlyBrowsedViewModel
    private val adapter: BrowsedAdapter by lazy {
        BrowsedAdapter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecentlyBrowsedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun injectFragmentAndVieModel() {
        (activity as MainActivity).mainComponent.inject(this)
        viewModel = ViewModelFactory(
            getRecordsUseCase = getRecordsUseCase,
            deleteRecordUseCase = deleteRecordUseCase,
            clearAllRecordsUseCase = clearAllRecordsUseCase
        ).create(RecentlyBrowsedViewModel::class.java)
    }

    override fun bindViewModel() {
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
    }

    override fun setView() {
        binding.recentlyBrowsedRecycler.adapter = adapter
    }

    override fun setObservers() {
        viewModel.browsedList.observe(viewLifecycleOwner) { browsed ->
            adapter.submitList(browsed)
        }
    }

    override fun browse(keyword: String) {
        mainViewModel.selectBrowsedRecord(keyword)
        findNavController().popBackStack()
    }

    override fun delete(record: BrowsedRecordEntity) {
        viewModel.deleteRecord(record)
    }
}
