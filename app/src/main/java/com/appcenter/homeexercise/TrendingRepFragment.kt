package com.appcenter.homeexercise

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.appcenter.homeexercise.adapter.TrendingAdapter
import com.appcenter.homeexercise.databinding.SystemBusyLayoutBinding
import com.appcenter.homeexercise.databinding.TrendingFragmentLayoutBinding
import com.appcenter.homeexercise.models.TrendingState
import com.appcenter.homeexercise.network.Result
import com.appcenter.homeexercise.utils.ViewUtils.remove
import com.appcenter.homeexercise.utils.ViewUtils.show
import com.appcenter.homeexercise.viewmodels.HomeViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@Suppress("UNCHECKED_CAST")
class TrendingRepFragment : Fragment() {

    private  var systemBusyLayoutBinding: SystemBusyLayoutBinding?=null
    private lateinit var trendingAdapter: TrendingAdapter
    private lateinit var trendingFragmentLayoutBinding: TrendingFragmentLayoutBinding


    private val homeViewModel: HomeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        trendingFragmentLayoutBinding = TrendingFragmentLayoutBinding.inflate(inflater, container, false)
        return trendingFragmentLayoutBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        setList()
        typingListener()
        hideShimmer()


    }

    private fun typingListener() {
        trendingFragmentLayoutBinding.searchField.addTextChangedListener {

            if (it.toString().length>=3){
                hideError()
                fetchTrendingRepos(searchRepo = it.toString())
            }
        }
    }

    private fun setListeners() {
         systemBusyLayoutBinding=SystemBusyLayoutBinding.bind(trendingFragmentLayoutBinding.root)
        systemBusyLayoutBinding?.retryButton?.setOnClickListener {
            hideError()
            fetchTrendingRepos(searchRepo =trendingFragmentLayoutBinding.searchField.text.toString())
        }

    }

    private fun fetchTrendingRepos(isRefresh:Boolean=false,searchRepo:String) {
        lifecycleScope.launch {
            homeViewModel.trendingRepos(1,isRefresh,searchRepo).collect {
                when (it) {
                    is Result.Success<*> -> {
                        hideShimmer()
                        hideError()
                        showList()
                        trendingAdapter.setData(it.data as List<TrendingState>)
                        Log.e(TrendingRepFragment::class.simpleName, "Success view")
                    }
                    is Result.Error<*> -> {
                        hideShimmer()
                        showError(it.message)
                        Log.e(TrendingRepFragment::class.simpleName, "Error view")
                    }
                    is Result.Loading -> {showShimmer()}
                }
            }


        }
    }

    private fun showList() {
        trendingFragmentLayoutBinding.tradingListId.show()
    }
    private fun hideList() {
        trendingFragmentLayoutBinding.tradingListId.remove()
    }
    private fun showError(error:String) {
        hideShimmer()
        trendingFragmentLayoutBinding.tradingListId.remove()
        trendingFragmentLayoutBinding.systemBusyId.parent?.let {
            trendingFragmentLayoutBinding.systemBusyId.inflate()
            systemBusyLayoutBinding?.errorDescription?.text=error
            setListeners()
        }?:kotlin.run {
            systemBusyLayoutBinding?.errorDescription?.text=error

        }
        systemBusyLayoutBinding?.topContainer?.show()
    }
    private fun hideError() {
        systemBusyLayoutBinding?.topContainer?.remove()
    }

    private fun hideShimmer() {
        trendingFragmentLayoutBinding.shimmerLayoutId.shimmerFrameLayout.stopShimmer()
        trendingFragmentLayoutBinding.shimmerLayoutId.shimmerFrameLayout.remove()
    }
    private fun showShimmer() {
        hideList()
        trendingFragmentLayoutBinding.shimmerLayoutId.shimmerFrameLayout.startShimmer()
        trendingFragmentLayoutBinding.shimmerLayoutId.shimmerFrameLayout.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        trendingFragmentLayoutBinding.shimmerLayoutId.shimmerFrameLayout.stopShimmer()
    }

    private fun setList() {
         trendingAdapter = TrendingAdapter()
        trendingFragmentLayoutBinding.tradingListId.layoutManager = LinearLayoutManager(requireContext())
        trendingFragmentLayoutBinding.tradingListId.adapter = trendingAdapter

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings ->{
                fetchTrendingRepos(true, searchRepo = trendingFragmentLayoutBinding.searchField.text.toString())
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}