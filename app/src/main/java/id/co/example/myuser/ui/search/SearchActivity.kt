package id.co.example.myuser.ui.search

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.app.ShareCompat
import androidx.recyclerview.widget.LinearLayoutManager
import id.co.example.myuser.R
import id.co.example.myuser.databinding.ActivitySearchBinding
import id.co.example.myuser.helper.Constant.USER_KEY
import id.co.example.myuser.helper.ShareUser
import id.co.example.myuser.model.response.UserSearchItem
import id.co.example.myuser.ui.adapter.ListSearchAdapter
import id.co.example.myuser.ui.detail.DetailUserActivity
import id.co.example.myuser.ui.viewmodel.UserViewModel

class SearchActivity : AppCompatActivity(), ShareUser {

    private val listSearchAdapter = ListSearchAdapter(this)
    private lateinit var binding: ActivitySearchBinding

    private val userViewModel: UserViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        searchUser()
        userViewModel.listSearch.observe(this, { listSearch ->
            binding.progressBar.visibility = View.GONE
            listSearch?.let { listSearchAdapter.setSearch(it) }
            listSearchAdapter.notifyDataSetChanged()
        })
        userViewModel.isLoading.observe(this, {
            showLoading(it)
        })

        setSearchUser()
    }

    private fun setSearchUser() {
        with(binding.rvSearchUser) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = listSearchAdapter
        }
        listSearchAdapter.setOnItemClickCallback(object : ListSearchAdapter.OnItemClickCallback {
            override fun onItemClicked(data: UserSearchItem) {
                goToDetail(data)
            }

        })
    }

    private fun searchUser() {
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager

        with(binding) {
            edtSearch.setSearchableInfo(searchManager.getSearchableInfo(componentName))
            edtSearch.queryHint = resources.getString(R.string.label_search_username)
            edtSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    userViewModel.getSearch(query.toString())
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return false
                }
            })
        }


    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun goToDetail(data: UserSearchItem) {
        val intent = Intent(this, DetailUserActivity::class.java)
        intent.putExtra(USER_KEY, data)
        startActivity(intent)
    }

    override fun onShareClick(userSearch: UserSearchItem) {
        val mimeType = getString(R.string.label_text_plain)

        ShareCompat.IntentBuilder(this)
            .setType(mimeType)
            .setChooserTitle(getString(R.string.label_share_user))
            .setText("User dengan login ${userSearch.login} ber-id dengan ${userSearch.id}")
            .startChooser()
    }
}