package com.example.runningtracker.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.runningtracker.R
import com.example.runningtracker.db.RunDao
import com.example.runningtracker.other.Constants.ACTION_SHOW_TRACKING_FRAGMENT
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigateToTrackingFragmentIfNeeded(intent)
     setSupportActionBar(runningToolbar)
        bottomNavigationView.setupWithNavController(navHostFragment.findNavController())
        bottomNavigationView.setOnNavigationItemReselectedListener {  }
      //  navHostFragment.findNavController().addOnDestinationChangedListener{ _, destinations, _ - >}
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        navigateToTrackingFragmentIfNeeded(intent)
    }

    private fun navigateToTrackingFragmentIfNeeded(intent: Intent?){
        if(intent?.action == ACTION_SHOW_TRACKING_FRAGMENT){
navHostFragment.findNavController().navigate(R.id.action_global_trackingFragment)
        }
    }
}