package com.example.quizlet

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.quizlet.databinding.ActivityOnboardBinding
import com.squareup.picasso.Picasso

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

class OnBoardData(val image: Int, val title: Int)
class ViewPagerAdapter(
    private val context: Context,
    private val data: ArrayList<OnBoardData>
) :
    RecyclerView.Adapter<ViewPagerAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivOnboard = itemView.findViewById<ImageView>(R.id.iv_onboard)!!
        val tvTitle = itemView.findViewById<TextView>(R.id.tv_title)!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.item_viewpager2_onboard, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]

        Picasso.get().load(item.image).into(holder.ivOnboard)
        holder.tvTitle.text = context.getString(item.title)
    }

}
