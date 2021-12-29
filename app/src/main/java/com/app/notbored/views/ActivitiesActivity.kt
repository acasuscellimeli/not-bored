package com.app.notbored.views

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.app.notbored.R
import com.app.notbored.recyclerView.ActivityAdapter
import com.app.notbored.databinding.ActivityActivitiesBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ActivitiesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityActivitiesBinding
    private lateinit var adapter: ActivityAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityActivitiesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        recyclerViewInit()
        configurateView()
    }



    private fun configurateView() {
        with(binding){
            toolBar.icLeft.visibility = View.GONE
            toolBar.toolbarTittle.text = getString(R.string.activities)
            toolBar.icRight.setBackgroundResource(R.drawable.ic_random)
            toolBar.icRight.setOnClickListener {
                println("Random")
            }
        }
    }

    private fun recyclerViewInit(){
        adapter = ActivityAdapter(this)
        binding.activitiesRecyclerView.adapter = adapter
    }

    fun searchRandom(query:String){




    }



}