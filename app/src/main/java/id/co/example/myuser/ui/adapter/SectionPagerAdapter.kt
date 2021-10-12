package id.co.example.myuser.ui.adapter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import id.co.example.myuser.ui.follower.FollowerFragment
import id.co.example.myuser.ui.following.FollowingFragment

class SectionPagerAdapter(activity: AppCompatActivity, private var bundle: Bundle): FragmentStateAdapter(activity) {

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> {
                fragment = FollowerFragment()
                fragment.arguments = bundle
            }
            1 -> {
                fragment = FollowingFragment()
                fragment.arguments = bundle
            }
        }
        return fragment as Fragment
    }

    override fun getItemCount(): Int = 2
}