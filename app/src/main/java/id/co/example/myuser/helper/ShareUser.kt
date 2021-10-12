package id.co.example.myuser.helper

import id.co.example.myuser.model.remote.response.UserSearchItem

interface ShareUser {
    fun onShareClick(userSearch: UserSearchItem)
}