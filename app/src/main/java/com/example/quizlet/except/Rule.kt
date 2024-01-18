package com.example.quizlet.except

import android.text.Spannable
import android.text.SpannableString
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.TextView
import com.example.quizlet.R

class Rule private constructor(){
    private lateinit var textView: TextView
    private lateinit var rule: String
    private lateinit var termOfService: String
    private lateinit var privacyPolicy: String
    private var textColor: Int = 0
    private var highlightColor: Int = 0
    private var transparentColor: Int = 0
    fun init() {
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

    @Suppress("DEPRECATION")
    class Builder {
        private val ruleInstance = Rule()

        // set default value
        init {
            ruleInstance.rule = ""
            ruleInstance.termOfService = ""
            ruleInstance.privacyPolicy = ""
            ruleInstance.textColor = 0
            ruleInstance.highlightColor = 0
            ruleInstance.transparentColor = 0
        }

        fun setTextView(textView: TextView): Builder {
            ruleInstance.textView = textView
            return this
        }

        fun setRule(rule: String): Builder {
            ruleInstance.rule = rule
            return this
        }

        fun setTermOfService(termOfService: String): Builder {
            ruleInstance.termOfService = termOfService
            return this
        }

        fun setPrivacyPolicy(privacyPolicy: String): Builder {
            ruleInstance.privacyPolicy = privacyPolicy
            return this
        }

        fun setTextColor(textColor: Int): Builder {
            ruleInstance.textColor = textColor
            return this
        }

        fun setHighlightColor(highlightColor: Int): Builder {
            ruleInstance.highlightColor = highlightColor
            return this
        }

        fun setTransparentColor(transparentColor: Int): Builder {
            ruleInstance.transparentColor = transparentColor
            return this
        }

        fun build(): Rule {
            with(ruleInstance){
                rule = if(ruleInstance.rule.isEmpty())
                    ruleInstance.textView.context.getString(R.string.rule)
                else rule

                termOfService = if(ruleInstance.termOfService.isEmpty())
                    ruleInstance.textView.context.getString(R.string.terms_of_service)
                else termOfService

                privacyPolicy = if(ruleInstance.privacyPolicy.isEmpty())
                    ruleInstance.textView.context.getString(R.string.privacy_policy)
                else privacyPolicy

                textColor = if(ruleInstance.textColor == 0)
                    ruleInstance.textView.context.resources.getColor(R.color.text_color_2)
                else textColor

                highlightColor = if(ruleInstance.highlightColor == 0)
                    ruleInstance.textView.context.resources.getColor(R.color.yellow_color)
                else highlightColor

                transparentColor = if(ruleInstance.transparentColor == 0)
                    ruleInstance.textView.context.resources.getColor(android.R.color.transparent)
                else transparentColor
            }
            return ruleInstance
        }
    }
}