package com.movingroot.flowassignment.data.api

import com.movingroot.flowassignment.data.model.ApiResult
import com.movingroot.flowassignment.data.model.Book
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("v1/search/book")
    suspend fun getBooks(
        @Query("query") keyword: String,
        @Query("display") cntPerPage: Int = 10,
        @Query("start") start: Int = 1,
        @Query("sort") sort: String = "sim" // sim : 정확도, date : 출간일
    ): Response<ApiResult<List<Book>>>

    /**
     * isbn 구판, 신판을 띄어쓰기로 함께 조회하면 위에서는 결과가 나오지 않지만 아래에서는 최신 판을 호출한다.
     * 따라서, 전부 int 이고 trim() 후에도 띄어쓰기가 존재할 경우에는 이 api 를 호출하여 결과를 조회한다.
     */
    @GET("v1/search/book_adv")
    suspend fun getBookDetails(
        @Query("display") cntPerPage: Int = 10,
        @Query("start") start: Int = 1,
        @Query("sort") sort: String = "sim",
        @Query("d_titl") title: String = "",
        @Query("d_isbn") isbn: String = ""
    ): Response<ApiResult<List<Book>>>
}
