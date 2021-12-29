package com.app.notbored.recyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.notbored.R
import com.app.notbored.constants.Constants

class ActivityAdapter() : RecyclerView.Adapter<ActivityViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivityViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ActivityViewHolder(layoutInflater.inflate(R.layout.activity_item, parent, false))
    }

    override fun onBindViewHolder(holder: ActivityViewHolder, position: Int) {
        val dogAtPosition = Constants.ACTIVITIES[position]
        holder.bind(dogAtPosition)
    }

    override fun getItemCount() = Constants.ACTIVITIES.size
}