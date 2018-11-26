package com.adibsurani.twitsplit.helper

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nshmura.snappysmoothscroller.LinearLayoutScrollVectorDetector
import com.nshmura.snappysmoothscroller.SnappySmoothScroller


class RecyclerUtil {
    companion object {

        fun setupVertical(recyclerView: RecyclerView, context: Context): RecyclerView {
            recyclerView.setHasFixedSize(false)
            recyclerView.isNestedScrollingEnabled = false
            val layoutManager = object : LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false) {
                override fun smoothScrollToPosition(
                    recyclerView: RecyclerView,
                    state: RecyclerView.State?,
                    position: Int
                ) {
                    val scroller = SnappySmoothScroller.Builder()
                        .setPosition(position)
                        .setScrollVectorDetector(LinearLayoutScrollVectorDetector(this))
                        .build(recyclerView.context)
                    startSmoothScroll(scroller)
                }
            }
            recyclerView.layoutManager = layoutManager
            return recyclerView
        }

        fun setupHorizontal(recyclerView: RecyclerView, context: Context): RecyclerView {
            recyclerView.setHasFixedSize(true)
            recyclerView.isNestedScrollingEnabled = false
            val layoutManager = object : LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false) {
                override fun smoothScrollToPosition(
                    recyclerView: RecyclerView,
                    state: RecyclerView.State?,
                    position: Int
                ) {
                    val scroller = SnappySmoothScroller.Builder()
                        .setPosition(position)
                        .setScrollVectorDetector(LinearLayoutScrollVectorDetector(this))
                        .build(recyclerView.context)
                    startSmoothScroll(scroller)
                }
            }
            recyclerView.layoutManager = layoutManager
            return recyclerView
        }
    }
}