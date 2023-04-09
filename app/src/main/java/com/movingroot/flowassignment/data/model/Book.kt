package com.movingroot.flowassignment.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Book(
    val title: String,
    val link: String,
    val image: String,
    val author: String,
    val discount: String,
    val publisher: String,
    val pubdate: String,
    val isbn: String,
    val description: String
) {
    val id: Long = try {
        isbn.toLong()
    } catch (e: NumberFormatException) {
        // ISBN10 마지막 글자 X 인 경우 대응
        isbn.dropLast(1).toLong() * 10
    }
}
