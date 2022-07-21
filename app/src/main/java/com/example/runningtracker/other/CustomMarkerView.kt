package com.example.runningtracker.other

import android.content.Context
import com.example.runningtracker.db.Run
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.utils.MPPointF

import kotlinx.android.synthetic.main.marker_view.view.*
import java.text.SimpleDateFormat
import java.util.*

class CustomMarkerView(
    val runs: List<Run>,
    context: Context,
    layoutId: Int
) : MarkerView(context,layoutId){

    override fun getOffset(): MPPointF {
        return MPPointF.getInstance(-width / 2f, -height.toFloat())
    }
    override fun refreshContent(e: Entry?, highlight: Highlight?) {
        super.refreshContent(e, highlight)
        if(e == null){
            return
        }
        val curRunId = e.x.toInt()
        val run= runs[curRunId]

        val calender = Calendar.getInstance().apply {
            timeInMillis = run.timeStamp
        }
        val dateFormate = SimpleDateFormat("dd.mm.yy", Locale.getDefault(),)
        tvDate.text = dateFormate.format(calender.time)

        val avgSpeed = "${run.avgSpeedInKMH}km/h"
        tvAvgSpeed.text = avgSpeed

        val distanceInKm = "${run.distanceInMeters / 1000f}Km"
        tvDistance.text = distanceInKm

        tvDuration.text = TrackingUtility.getFormattedStopWatchTime(run.timeInMillis)

        val caloriesBurned = "${run.caloriesBurned}KCal"
        tvCaloriesBurned.text = caloriesBurned
    }
}