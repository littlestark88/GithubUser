package id.co.example.myuser.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import id.co.example.myuser.databinding.ListUserFollowerBinding
import id.co.example.myuser.model.response.UserFollowerItem

class UserFollowerAdapter: RecyclerView.Adapter<UserFollowerAdapter.FollowerViewHolder>() {
    private val data = ArrayList<UserFollowerItem>()

    fun setFollower(dataFollower: List<UserFollowerItem>) {
        this.data.clear()
        this.data.addAll(dataFollower)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowerViewHolder {
        val itemFollowerUser = ListUserFollowerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FollowerViewHolder(itemFollowerUser)
    }

    override fun onBindViewHolder(holder: FollowerViewHolder, position: Int) {
        val dataList = data[position]
        holder.bindItem(dataList)
    }

    override fun getItemCount(): Int = data.size

    inner class FollowerViewHolder(private val binding: ListUserFollowerBinding): RecyclerView.ViewHolder(binding.root) {
        fun bindItem(dataFollower: UserFollowerItem) {
            with(binding) {
                tvName.text = dataFollower.login
                tvIdUsername.text = dataFollower.id.toString()
                Picasso
                    .get()
                    .load(dataFollower.avatarUrl)
                    .into(imgUser)
            }
        }

    }
}