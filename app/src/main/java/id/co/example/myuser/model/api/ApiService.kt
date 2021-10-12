package id.co.example.myuser.model.api

import id.co.example.myuser.model.response.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("search/users")
    fun getSearchUser(@Query("q")q: String): Call<UserSearchResponse>

    @GET("users/{username}")
    fun getDetailUser(@Path("username")username: String): Call<UserDetailResponse>

    @GET("users/{username}/followers")
    fun getFollowerUser(@Path("username")username: String): Call<List<UserFollowerItem>>

    @GET("users/{username}/following")
    fun getFollowingUser(@Path("username")username: String): Call<List<UserFollowingItem>>

    @GET("users/{username}/repos")
    fun getRepositoryUser(@Path("username")username: String): Call<List<UserRepositoryItem>>

}