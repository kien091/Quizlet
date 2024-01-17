package com.example.quizlet

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.example.quizlet.adapter.ViewPagerAdapter
import com.example.quizlet.databinding.ActivityOnboardBinding
import com.example.quizlet.except.Rule

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

        // handle with textview rule
        Rule.getInstance().setTextView(binding.tvRule)
        Rule.getInstance().init(binding.tvRule)

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

