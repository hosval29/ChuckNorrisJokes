package com.pujol.chucknorrisjokes.data.remote

import com.pujol.chucknorrisjokes.data.dto.CategoryDetailDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("categories")
    suspend fun getCategories(): List<String>
/*
    @GET("random?={category}")
    suspend fun getCategoryDetailTest(@Path("category") category: String): CategoryDetailDto
 */
    @GET("random?")
    suspend fun getCategoryDetail(@Query("category") category: String): CategoryDetailDto
}