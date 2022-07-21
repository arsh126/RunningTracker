package com.example.runningtracker.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.runningtracker.R
import com.example.runningtracker.db.Run
import com.example.runningtracker.other.TrackingUtility
import kotlinx.android.synthetic.main.item_run.view.*
import java.text.SimpleDateFormat
import java.util.*

class RunAdapter: RecyclerView.Adapter<RunAdapter.RunViewholder>(){
    inner class RunViewholder(itemView:View) : RecyclerView.ViewHolder(itemView)
    val diffCallback = object : DiffUtil.ItemCallback<Run>(){
        override fun areItemsTheSame(oldItem: Run, newItem: Run): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Run, newItem: Run): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    val differ = AsyncListDiffer(this, diffCallback)
    fun submitList(list: List<Run>) = differ.submitList(list)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RunViewholder {
       return RunViewholder(
           LayoutInflater.from(parent.context).inflate(
               R.layout.item_run
               ,parent,
               false
           )
       )
    }



    override fun onBindViewHolder(holder: RunViewholder, position: Int) {
       val run = differ.currentList[position]
        holder.itemView.apply {
            Glide.with(this).load(run.img).into(ivRunImage)
            val calender = Calendar.getInstance().apply {
                timeInMillis = run.timeStamp
            }
            val dateFormate = SimpleDateFormat("dd.mm.yy",Locale.getDefault(),)
            tvDate.text = dateFormate.format(calender.time)

            val avgSpeed = "${run.avgSpeedInKMH}km/h"
            tvAvgSpeed.text = avgSpeed
            val distanceInKm = "${run.distanceInMeters / 1000f}Km"
            tvDistance.text = distanceInKm

            tvTime.text = TrackingUtility.getFormattedStopWatchTime(run.timeInMillis)
            val caloriesBurned = "${run.caloriesBurned}KCal"
            tvCalories.text = caloriesBurned
        }
    }

    override fun getItemCount(): Int {
       return differ.currentList.size
    }
}