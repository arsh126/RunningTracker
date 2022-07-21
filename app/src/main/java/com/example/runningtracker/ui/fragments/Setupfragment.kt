package com.example.runningtracker.ui.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.example.runningtracker.R
import com.example.runningtracker.other.Constants.KEY_FRIST_TIME_TOGGLE
import com.example.runningtracker.other.Constants.KEY_NAME
import com.example.runningtracker.other.Constants.KEY_WEIGHT
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_setup.*
import javax.inject.Inject

@AndroidEntryPoint
class Setupfragment: Fragment(R.layout.fragment_setup){

    @Inject
    lateinit var SharedPref: SharedPreferences

    @set: Inject
    var isOpenAppFristTime =true

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(!isOpenAppFristTime){
            val navOptions = NavOptions.Builder()
                .setPopUpTo(R.id.setupfragment, true)
                .build()
            findNavController().navigate(R.id.action_setupfragment_to_runfragment, savedInstanceState, navOptions)
        }

        tvContinue.setOnClickListener {
            val succes = writePersonalDataToSharedPref()
            if(succes){
                findNavController().navigate(R.id.action_setupfragment_to_runfragment)
            }else{
                Snackbar.make(requireView(), "Please Enter all The Fields", Snackbar.LENGTH_LONG).show()
            }

        }
    }

    private fun writePersonalDataToSharedPref(): Boolean{
        val name = etName.text.toString()
        val weight = etWeight.text.toString()
        if(name.isEmpty() && weight.isEmpty()){
            return false
        }
        SharedPref.edit()
            .putString(KEY_NAME, name)
            .putFloat(KEY_WEIGHT, weight.toFloat())
            .putBoolean(KEY_FRIST_TIME_TOGGLE, true)
            .apply()

        val toolbarText = "let's go $name!"
        requireActivity().tvToolbarTitle.text = toolbarText
        return true
    }
}