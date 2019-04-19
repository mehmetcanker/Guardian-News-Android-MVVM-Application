package com.canker.core.ui.recyclerview

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class LayoutManager {

    fun createLinear(context: Context): RecyclerView.LayoutManager {
        return LinearLayoutManager(context)
    }
}