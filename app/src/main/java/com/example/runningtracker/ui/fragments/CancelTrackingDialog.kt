package com.example.runningtracker.ui.fragments

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.runningtracker.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class CancelTrackingDialog: DialogFragment(){

    private var yesListener: (() -> Unit)? = null

  fun  setYesListener(listener: () -> Unit){
yesListener = listener
    }



    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

       return MaterialAlertDialogBuilder(requireContext(), R.style.AlertDialogTheme)
            .setTitle("Cancel The Run")
            .setIcon(R.drawable.ic_delete)
            .setMessage("Are You Sure To Cancel the Current Run And Delete All Its Data")
            .setPositiveButton("Yes") { _, _ ->
                yesListener?.let { yes ->
                    yes()
                }

            }
            .setNegativeButton("No") { dialogInterface, _ ->
                dialogInterface.cancel()

            }.create()

    }

}