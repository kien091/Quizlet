package com.example.quizlet.except

import android.annotation.SuppressLint
import android.text.Spannable
import android.text.SpannableString
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.quizlet.R

class Rule{
    private lateinit var textView: TextView
    companion object {
        @SuppressLint("StaticFieldLeak")
        private var instance: Rule? = null
        fun getInstance(): Rule {
            if (instance == null) {
                instance = Rule()
            }
            return instance!!
        }
    }

    fun setTextView(textView: TextView) {
        this.textView = textView
    }

    fun init(textView: TextView) {
        val rule = ContextCompat.getString(textView.context, R.string.rule)
        val termOfService = ContextCompat.getString(textView.context, R.string.terms_of_service)
        val privacyPolicy = ContextCompat.getString(textView.context, R.string.privacy_policy)
        val textColor = ContextCompat.getColor(textView.context, R.color.text_color_2)
        val highlightColor = ContextCompat.getColor(textView.context, R.color.yellow_color)
        val transparentColor = ContextCompat.getColor(textView.context, android.R.color.transparent)

        val spannableString = SpannableString(rule)

        val termOfServiceClickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                // redirect to term of service
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.isUnderlineText = false
                ds.isFakeBoldText = true
                ds.color = textColor
                if (textView.isPressed)
                    textView.highlightColor = highlightColor
                else
                    textView.highlightColor = transparentColor
                textView.postInvalidate()
            }
        }
        val termOfServiceStart = rule.indexOf(termOfService)
        val termOfServiceEnd = termOfServiceStart + termOfService.length
        spannableString.setSpan(
            termOfServiceClickableSpan,
            termOfServiceStart,
            termOfServiceEnd,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        val privacyPolicyClickableSpan = object : ClickableSpan() {

            override fun onClick(widget: View) {
                // redirect to privacy policy
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.isUnderlineText = false
                ds.isFakeBoldText = true
                ds.color = textColor
                if (textView.isPressed)
                    textView.highlightColor = highlightColor
                else
                    textView.highlightColor = transparentColor
                textView.postInvalidate()
            }
        }
        val privacyPolicyStart = rule.indexOf(privacyPolicy)
        val privacyPolicyEnd = privacyPolicyStart + privacyPolicy.length
        spannableString.setSpan(
            privacyPolicyClickableSpan,
            privacyPolicyStart,
            privacyPolicyEnd,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )


        textView.text = spannableString
        textView.movementMethod = LinkMovementMethod.getInstance()
    }
}