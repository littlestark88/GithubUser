package id.co.example.myuser.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import id.co.example.myuser.databinding.ListUserItemBinding
import id.co.example.myuser.helper.ShareUser
import id.co.example.myuser.model.remote.response.UserSearchItem

class ListSearchAdapter(private val shareUser: ShareUser): RecyclerView.Adapter<ListSearchAdapter.SearchViewHolder>() {
    private val data = ArrayList<UserSearchItem>()
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    fun setSearch(dataSearch: List<UserSearchItem>) {
        this.data.clear()
        this.data.addAll(dataSearch)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val itemListUser = ListUserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchViewHolder(itemListUser)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val dataList = data[position]
        holder.bindItem(dataList)
    }

    override fun getItemCount(): Int = data.size

    inner class SearchViewHolder(private val binding: ListUserItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bindItem(data: UserSearchItem) {
            with(binding){
                tvName.text = data.login
                tvIdUsername.text = data.id.toString()
                Picasso
                    .get()
                    .load(data.avatarUrl)
                    .into(imgUser)
                imgShare.setOnClickListener {
                    shareUser.onShareClick(data)
                }
                itemView.setOnClickListener {
                    onItemClickCallback.onItemClicked(data)
                }
            }

        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: UserSearchItem)
    }
}