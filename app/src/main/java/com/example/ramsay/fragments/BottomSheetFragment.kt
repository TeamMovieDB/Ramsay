package com.example.ramsay.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.ramsay.R
import com.google.android.flexbox.FlexboxLayout
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetFragment(private val bottomSheetContext: Context?) : BottomSheetDialogFragment() {

    private var restNames: MutableList<String> = mutableListOf()
    private var tags: MutableList<TextView> = mutableListOf()
    private lateinit var flexBoxLayoutGroups: FlexboxLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottom_sheet_dialog, container, false)
    }

    override fun setArguments(args: Bundle?) {
        super.setArguments(args)
        restNames = args?.get("tags") as MutableList<String>
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViews(view)
        setTagsList(restNames)
        Log.d("testt", tags.toString())
    }

    private fun bindViews(view: View) = with(view) {
        flexBoxLayoutGroups = findViewById(R.id.flexBoxLayoutGroups)
    }

    private fun setTag(tag: String?): TextView {
        val textView = TextView(bottomSheetContext)
        textView.text = tag
        textView.textSize = 12.0f
        return textView
    }

    private fun setTagsList(listOfNames: List<String>) {
        for (i in listOfNames) {
            tags.add(setTag(i))
        }
        setFlexboxViews()
    }

    private fun setFlexboxViews() {
        for (i in tags)
            flexBoxLayoutGroups.addView(i)
    }
}
