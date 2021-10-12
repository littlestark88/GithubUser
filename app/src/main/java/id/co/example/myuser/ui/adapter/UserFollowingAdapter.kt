package id.co.example.myuser.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import id.co.example.myuser.databinding.ListUserFollowerBinding
import id.co.example.myuser.model.remote.response.UserFollowingItem

class UserFollowingAdapter: RecyclerView.Adapter<UserFollowingAdapter.FollowingViewHolder>() {
    private val data = ArrayList<UserFollowingItem>()

    fun setFollowing(dataFollowing: List<UserFollowingItem>) {
        this.data.clear()
        this.data.addAll(dataFollowing)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowingViewHolder {
        val itemFollowingUser = ListUserFollowerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FollowingViewHolder(itemFollowingUser)
    }

    override fun onBindViewHolder(holder: FollowingViewHolder, position: Int) {
        val dataList = data[position]
        holder.bindItem(dataList)
    }

    override fun getItemCount(): Int = data.size

    class FollowingViewHolder(private val binding: ListUserFollowerBinding): RecyclerView.ViewHolder(binding.root) {
        fun bindItem(dataFollowing: UserFollowingItem) {
            with(binding) {
                tvName.text = dataFollowing.login
                tvIdUsername.text = dataFollowing.id.toString()
                Picasso
                    .get()
                    .load(dataFollowing.avatarUrl)
                    .into(imgUser)
            }
        }

    }

}