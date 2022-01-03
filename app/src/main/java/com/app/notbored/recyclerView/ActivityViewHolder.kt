package com.app.notbored.recyclerView
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.app.notbored.R
import com.app.notbored.databinding.ActivityItemBinding
import com.app.notbored.service.ActivitySevice
import com.app.notbored.utils.*
import com.app.notbored.views.ActivitiesActivity

class ActivityViewHolder(view: View, var activity: ActivitiesActivity) : RecyclerView.ViewHolder(view) {
    private val binding = ActivityItemBinding.bind(view)
    fun bind(activityDescription:String){
        with(binding){
            activityTittle.text = activityDescription.toCapitalize()
            //If the category icon does not exist, an empty image will be displayed
            val imgResource = activity.resources.getIdentifier("ic_$activityDescription", "drawable", activity.packageName)
            icActivity.setImageResource(imgResource)
            itemContainer.setOnClickListener {
                activity.searchActivity(type = activityDescription)
            }
        }
    }
}
