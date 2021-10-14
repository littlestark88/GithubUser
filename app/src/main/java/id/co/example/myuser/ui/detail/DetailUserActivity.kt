package id.co.example.myuser.ui.detail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.squareup.picasso.Picasso
import id.co.example.myuser.R
import id.co.example.myuser.databinding.ActivityDetailUserBinding
import id.co.example.myuser.helper.Constant.USERNAME
import id.co.example.myuser.helper.Constant.USER_KEY
import id.co.example.myuser.model.response.UserSearchItem
import id.co.example.myuser.ui.adapter.SectionPagerAdapter
import id.co.example.myuser.ui.viewmodel.UserViewModel

class DetailUserActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityDetailUserBinding
    private val userViewModel: UserViewModel by viewModels()

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.label_follower,
            R.string.label_following
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.appBarUser.imgBack.setOnClickListener(this)
        val data = intent.getParcelableExtra<UserSearchItem>(USER_KEY)
        val bundle = Bundle()
        bundle.putString(USERNAME, data?.login)
        userViewModel.getDetail(data?.login.toString())
        userViewModel.getFollower(data?.login.toString())
        userViewModel.getFollowing(data?.login.toString())
        userViewModel.getRepository(data?.login.toString())
        val sectionPagerAdapter = SectionPagerAdapter(this, bundle)
        val viewPager2: ViewPager2 = findViewById(R.id.viewPager)
        viewPager2.adapter = sectionPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        TabLayoutMediator(tabs, viewPager2) { TABS, position ->
            TABS.text = resources.getString(TAB_TITLES[position])
        }.attach()


        supportActionBar?.elevation = 0f
        userViewModel.detail.observe(this, { detail ->
            with(binding) {
                appBarUser.tvName.text = detail.login
                tvCompany.text = detail.company
                tvLocation.text = detail.location
                tvUsername.text = detail.name
                Picasso
                    .get()
                    .load(detail.avatarUrl)
                    .into(imgUser)
            }
        })
        userViewModel.listFollower.observe(this, {
            binding.tvFollowerValue.text = it.size.toString()
        })
        userViewModel.listFollowing.observe(this, {
            binding.tvFollowingValue.text = it.size.toString()
        })
        userViewModel.listRepository.observe(this, {
            binding.tvRepositoryValue.text = it.size.toString()
        })
    }

    override fun onClick(view: View) {
        when (view) {
            binding.appBarUser.imgBack -> onBackPressed()
        }
    }
}