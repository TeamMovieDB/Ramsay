package com.example.ramsay.ui

import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.ramsay.R
import com.example.ramsay.model.Customer
import com.squareup.picasso.Picasso

class CollapsingToolbarBottom : ConstraintLayout {
    lateinit var tvNickname: TextView
    lateinit var ivAvatar: ImageView
    private var rotationAngle = 0
    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        init(context)
    }

    private fun init(context: Context){
        LinearLayout.inflate(context, R.layout.collapsing_toolbar_bottom, this)
        tvNickname = findViewById(R.id.tvNickname)
        ivAvatar = findViewById(R.id.ivAvatar)
    }

    fun setUserHalfData(customer: Customer){
        setUsername(customer.username)
//        setAvatar(customer.image)
    }

    private fun setUsername(username: String){
        tvNickname.text = username
    }
    private fun setAvatar(image: String){
        Picasso.get().load(image).into(ivAvatar)
    }


}