package com.app.notbored.recyclerView
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.app.notbored.databinding.ActivityItemBinding
import com.app.notbored.service.ActivitySevice
import com.app.notbored.utils.*
import com.app.notbored.views.ActivitiesActivity

class ActivityViewHolder(view: View,var activity: ActivitiesActivity) : RecyclerView.ViewHolder(view) {
    private val binding = ActivityItemBinding.bind(view)
    fun bind(activityPosition:String){
        with(binding){
            binding.activityTittle.text = activityPosition.toCapitalize()

            binding.itemContainer.setOnClickListener {
                ActivitySevice().searchRandom(activity)
            }
        }
    }
}
