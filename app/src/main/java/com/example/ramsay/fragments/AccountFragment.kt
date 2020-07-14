package com.example.ramsay.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.ramsay.R
import com.google.android.material.bottomsheet.BottomSheetDialog

class AccountFragment : Fragment() {
    private var bottomSheetDialog: BottomSheetDialog?=null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.faq_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        callingBottomSheetDialog()
    }
    private fun callingBottomSheetDialog(){
        bottomSheetDialog = context?.let { BottomSheetDialog(it) }
        bottomSheetDialog?.setContentView(R.layout.bottom_sheet_dialog)
        bottomSheetDialog?.setCanceledOnTouchOutside(false)
        bindingBottomSheetViews()
        bottomSheetDialog?.show()
    }

    private fun bindingBottomSheetViews(){
    }
}


