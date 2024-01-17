package com.example.quizlet

import android.content.Intent
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.example.quizlet.adapter.ViewPagerAdapter
import com.example.quizlet.databinding.ActivityOnboardBinding

@Suppress("DEPRECATION")
class OnboardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOnboardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_onboard)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setLogo(R.drawable.quizlet_logo)



        // init onboarding screen and indicator
        val data = initData()
        val adapter = ViewPagerAdapter(this, data)
        binding.viewPager.adapter = adapter
        binding.viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        binding.indicator.setViewPager(binding.viewPager)



        // handle string rule
        val spannableString = SpannableString(resources.getString(R.string.rule))

        val termOfServiceClickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                // redirect to term of service
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.isUnderlineText = false
                ds.isFakeBoldText = true
                ds.color = resources.getColor(R.color.text_color_2)
            }
        }
        val termOfServiceStart = resources.getString(R.string.rule).indexOf(resources.getString(R.string.terms_of_service))
        val termOfServiceEnd = termOfServiceStart + resources.getString(R.string.terms_of_service).length
        spannableString.setSpan(termOfServiceClickableSpan, termOfServiceStart, termOfServiceEnd, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        val privacyPolicyClickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                // redirect to privacy policy
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.isUnderlineText = false
                ds.isFakeBoldText = true
                ds.color = resources.getColor(R.color.text_color_2)
            }
        }
        val privacyPolicyStart = resources.getString(R.string.rule).indexOf(resources.getString(R.string.privacy_policy))
        val privacyPolicyEnd = privacyPolicyStart + resources.getString(R.string.privacy_policy).length
        spannableString.setSpan(privacyPolicyClickableSpan, privacyPolicyStart, privacyPolicyEnd, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        binding.tvRule.text = spannableString
        binding.tvRule.movementMethod = LinkMovementMethod.getInstance()

        binding.btnLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
        binding.btnRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    // init data for viewpager
    private fun initData(): ArrayList<OnBoardData>{
        val data = ArrayList<OnBoardData>()
        data.add(
            OnBoardData(
                R.drawable.onboard_1,
                R.string.onboard_1_title
            )
        )
        data.add(
            OnBoardData(
                R.drawable.onboard_2,
                R.string.onboard_2_title
            )
        )
        data.add(
            OnBoardData(
                R.drawable.onboard_3,
                R.string.onboard_3_title
            )
        )
        data.add(
            OnBoardData(
                R.drawable.onboard_4,
                R.string.onboard_4_title
            )
        )
        return data
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val itemId = 0
        val btnFind: MenuItem = menu!!.add(Menu.NONE, itemId, Menu.NONE, "Find")
        btnFind.setIcon(R.drawable.icon_find)
        btnFind.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)

        return true
    }
}

