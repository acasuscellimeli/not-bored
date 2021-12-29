package com.app.notbored.recyclerView
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.app.notbored.databinding.ActivityItemBinding
import com.app.notbored.utils.*
import java.util.*

class ActivityViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ActivityItemBinding.bind(view)
    fun bind(activityPosition:String){
        with(binding){
            binding.activityTittle.text = activityPosition.toCapitalize()

            binding.test.setOnClickListener {
                println(activityPosition)
            }
        }
    }
}
