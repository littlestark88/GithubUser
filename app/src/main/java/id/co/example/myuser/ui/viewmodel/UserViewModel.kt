package id.co.example.myuser.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.co.example.myuser.helper.Constant.TAG
import id.co.example.myuser.model.api.ApiConfig
import id.co.example.myuser.model.response.*
import retrofit2.Call
import retrofit2.Response

class UserViewModel : ViewModel() {

    private val _listSearch = MutableLiveData<List<UserSearchItem>?>()
    val listSearch: LiveData<List<UserSearchItem>?> = _listSearch

    private val _detail = MutableLiveData<UserDetailResponse>()
    val detail: LiveData<UserDetailResponse> = _detail

    private val _listFollower = MutableLiveData<List<UserFollowerItem>>()
    val listFollower: LiveData<List<UserFollowerItem>> = _listFollower

    private val _listFollowing = MutableLiveData<List<UserFollowingItem>>()
    val listFollowing: LiveData<List<UserFollowingItem>> = _listFollowing

    private val _listRepository = MutableLiveData<List<UserRepositoryItem>>()
    val listRepository: LiveData<List<UserRepositoryItem>> = _listRepository

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun getSearch(q: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getSearchUser(q)
        client.enqueue(object : retrofit2.Callback<UserSearchResponse>{
            override fun onResponse(
                call: Call<UserSearchResponse>,
                response: Response<UserSearchResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _listSearch.value = response.body()?.items
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<UserSearchResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun getDetail(username: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getDetailUser(username)
        client.enqueue(object : retrofit2.Callback<UserDetailResponse>{
            override fun onResponse(
                call: Call<UserDetailResponse>,
                response: Response<UserDetailResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _detail.value = response.body()
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<UserDetailResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun getFollower(username: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getFollowerUser(username)
        client.enqueue(object : retrofit2.Callback<List<UserFollowerItem>>{
            override fun onResponse(
                call: Call<List<UserFollowerItem>>,
                response: Response<List<UserFollowerItem>>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _listFollower.value = response.body()
                } else {
                    Log.e(TAG, "onFailurefollower: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<UserFollowerItem>>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailurefollowerf: ${t.message.toString()}")
            }
        })
    }

    fun getFollowing(username: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getFollowingUser(username)
        client.enqueue(object : retrofit2.Callback<List<UserFollowingItem>>{
            override fun onResponse(
                call: Call<List<UserFollowingItem>>,
                response: Response<List<UserFollowingItem>>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _listFollowing.value = response.body()
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<UserFollowingItem>>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun getRepository(username: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getRepositoryUser(username)
        client.enqueue(object : retrofit2.Callback<List<UserRepositoryItem>>{
            override fun onResponse(
                call: Call<List<UserRepositoryItem>>,
                response: Response<List<UserRepositoryItem>>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _listRepository.value = response.body()
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<UserRepositoryItem>>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

}