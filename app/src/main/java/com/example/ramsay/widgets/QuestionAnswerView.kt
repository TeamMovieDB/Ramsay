package com.example.ramsay.widgets

import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.ramsay.R

class QuestionAnswerView : ConstraintLayout {
    private lateinit var tvQuestion: TextView
    private lateinit var tvAnswer: TextView
    private lateinit var ivHider: ImageView
    private lateinit var clLayoutTop: ConstraintLayout
    private var question: String = ""
    private var answer: String = ""
    private var isShowing: Boolean = true
    private var rotationAngle = 0

    constructor(context: Context) : super(context) {
        bindViews(context)
    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        bindViews(context)
        init(attributeSet)
    }

    private fun bindViews(context: Context) {
        LinearLayout.inflate(context, R.layout.question_answer_layout, this)
        tvQuestion = findViewById(R.id.tvQuestion)
        tvAnswer = findViewById(R.id.tvAnswer)
        ivHider = findViewById(R.id.ivHider)
        clLayoutTop = findViewById(R.id.clLayoutTop)
        clLayoutTop.setOnClickListener {
            val linesCount = tvAnswer.lineCount
            showAnswer(linesCount)
        }
        expandCollapse(tvAnswer, tvAnswer.lineCount, 0)
    }

    private fun init(attributeSet: AttributeSet) {
        attributeSet.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.QuestionAnswerView)
            question = typedArray.getString(R.styleable.QuestionAnswerView_questionText).toString()
            answer = typedArray.getString(R.styleable.QuestionAnswerView_answerText).toString()
            tvQuestion.text = question
            tvAnswer.text = answer
            typedArray.recycle()
        }
    }

    fun setArguments(question: String, answer: String) {
        tvQuestion.text = question
        tvAnswer.text = answer
    }

    private fun showAnswer(maxLineCount: Int) {
        isShowing = when (isShowing) {
            false -> {
                expandCollapse(tvAnswer, maxLineCount, 0)
                rotateHider()
                true
            }
            true -> {
                expandCollapse(tvAnswer, 0, maxLineCount)
                rotateHider()
                false
            }
        }
    }

    private fun expandCollapse(textView: TextView, startLine: Int, endLine: Int) {
        val animation = ObjectAnimator.ofInt(textView, "maxLines", startLine, endLine)
        animation.setDuration(100).start()
    }

    private fun rotateHider() {
        val anim: ObjectAnimator = ObjectAnimator.ofFloat(
            ivHider, "rotation",
            rotationAngle.toFloat(), (rotationAngle + 180).toFloat()
        )
        anim.duration = 200
        anim.start()
        rotationAngle += 180
        rotationAngle %= 360
    }
}