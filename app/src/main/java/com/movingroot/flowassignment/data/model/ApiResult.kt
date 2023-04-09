package com.movingroot.flowassignment.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiResult<T>(
    val lastBuildDate: String,
    val total: Int,
    val start: Int,
    val display: Int,
    val items: T
)
