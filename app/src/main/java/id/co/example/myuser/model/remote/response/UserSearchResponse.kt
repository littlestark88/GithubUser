package id.co.example.myuser.model.remote.response

import com.google.gson.annotations.SerializedName

data class UserSearchResponse(

	@field:SerializedName("total_count")
	val totalCount: Int? = 0,

	@field:SerializedName("incomplete_results")
	val incompleteResults: Boolean? = false,

	@field:SerializedName("items")
	val items: List<UserSearchItem>? = null
)
