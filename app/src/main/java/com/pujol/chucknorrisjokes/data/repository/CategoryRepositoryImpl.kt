package com.pujol.chucknorrisjokes.data.repository

import com.pujol.chucknorrisjokes.core.util.Resource
import com.pujol.chucknorrisjokes.data.mapper.toCategoryDetail
import com.pujol.chucknorrisjokes.data.remote.ApiService
import com.pujol.chucknorrisjokes.domain.model.CategoryDetail
import com.pujol.chucknorrisjokes.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(private val service: ApiService) :
    CategoryRepository {
    override suspend fun getCategories(): Flow<Resource<List<String>>> = flow {
        emit(Resource.Loading(true))
        try {
            val response = service.getCategories()
            emit(Resource.Loading(false))
            emit(Resource.Success(response))
        } catch (e: HttpException) {
            emit(Resource.Loading(false))
            emit(Resource.Error(message = e.message()))
        } catch (e: IOException) {
            emit(Resource.Loading(false))
            emit(Resource.Error(message = e.message))
        }
    }

    override suspend fun getCategoryDetail(category: String): Flow<Resource<CategoryDetail>> =
        flow {
            emit(Resource.Loading(true))
            try {
                val response = service.getCategoryDetail(category).toCategoryDetail()
                emit(Resource.Loading(false))
                emit(Resource.Success(response))
            } catch (e: HttpException) {
                emit(Resource.Loading(false))
                emit(Resource.Error(message = e.message()))
            } catch (e: IOException) {
                emit(Resource.Loading(false))
                emit(Resource.Error(message = e.message))
            }
        }
}