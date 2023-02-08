package com.pujol.chucknorrisjokes.domain.repository

import com.pujol.chucknorrisjokes.core.util.Resource
import com.pujol.chucknorrisjokes.domain.model.CategoryDetail
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {

    suspend fun getCategories(): Flow<Resource<List<String>>>

    suspend fun getCategoryDetail(category: String): Flow<Resource<CategoryDetail>>

}