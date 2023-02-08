package com.pujol.chucknorrisjokes.domain.use_case

import com.pujol.chucknorrisjokes.core.util.Resource
import com.pujol.chucknorrisjokes.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(private val repository: CategoryRepository) {
    suspend operator fun invoke(): Flow<Resource<List<String>>> = flow {
        repository.getCategories().collect { resource ->
            when (resource) {
                is Resource.Success -> {
                    emit(Resource.Success(resource.data))
                }
                is Resource.Error -> {
                    emit(Resource.Error())
                }
                is Resource.Loading -> {
                    emit(Resource.Loading(resource.loading))
                }
            }
        }
    }
}