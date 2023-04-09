package com.appcenter.homeexercise.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.appcenter.homeexercise.databinding.TradingAdapterLayoutBinding
import com.appcenter.homeexercise.models.TrendingState
import com.appcenter.homeexercise.utils.ImageLoaderUtils
import com.appcenter.homeexercise.utils.TrendingDiffUtilCallback

class TrendingAdapter : RecyclerView.Adapter<TrendingAdapter.ViewHolder>() {

    private val trendingList = ArrayList<TrendingState>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
         val binding =TradingAdapterLayoutBinding.inflate(inflater,parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.ownerNameId.text=trendingList[position].userName
        holder.binding.repoNameId.text=trendingList[position].repoName
        holder.binding.repoLanguageId.text=trendingList[position].language
        holder.binding.repoDescriptionId.text=trendingList[position].description
        holder.binding.repoStarId.text=trendingList[position].starCount.toString()
        ImageLoaderUtils.loadImageFromServerCrop(trendingList[position].image?:"",holder.binding.imagePlaceHolderId)
    }

    fun setData(newTrendingList: List<TrendingState>) {
        val diffCallback = TrendingDiffUtilCallback(trendingList, newTrendingList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        trendingList.clear()
        trendingList.addAll(newTrendingList)
        diffResult.dispatchUpdatesTo(this)
       
    }

    override fun getItemCount(): Int {
        return trendingList.size
    }

    class ViewHolder(val binding: TradingAdapterLayoutBinding) : RecyclerView.ViewHolder(binding.root)
}