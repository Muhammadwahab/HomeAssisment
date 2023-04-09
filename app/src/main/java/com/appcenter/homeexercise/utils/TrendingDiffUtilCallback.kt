package com.appcenter.homeexercise.utils

import androidx.recyclerview.widget.DiffUtil
import com.appcenter.homeexercise.models.TrendingState

class TrendingDiffUtilCallback(
    private val oldList: List<TrendingState>,
    private val newList: List<TrendingState>
) : DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldPosition: Int, newPosition: Int) =
        oldList[oldPosition].id == newList[newPosition].id

    override fun areContentsTheSame(oldPosition: Int, newPosition: Int) =
        oldList[oldPosition] == newList[newPosition]
}