package com.example.quizlet

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.example.quizlet.adapter.ViewPagerAdapter
import com.example.quizlet.databinding.ActivityOnboardBinding

class OnboardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOnboardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_onboard)

        val data = initData()
        val adapter = ViewPagerAdapter(this, data)
        binding.viewPager.adapter = adapter
        binding.viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        binding.indicator.setViewPager(binding.viewPager)

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
}

