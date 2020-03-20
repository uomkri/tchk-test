package com.tchair.tchktest.net

import com.squareup.moshi.Json

data class UserData(
    val id: Int,
    val login: String,
    @Json(name = "avatar_url")
    val avatarUrl: String,
    val type: String
)

data class SearchResult(
    @Json(name = "total_count")
    val totalCount: Int,
    val items: List<UserData>
)

data class User(
    val id: Int,
    val login: String,
    @Json(name = "avatar_url")
    val avatarUrl: String,
    val company: String,
    val type: String,
    val name: String
)