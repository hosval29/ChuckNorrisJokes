package com.pujol.chucknorrisjokes.domain.use_case

import com.pujol.chucknorrisjokes.core.util.Resource
import com.pujol.chucknorrisjokes.domain.model.CategoryDetail
import com.pujol.chucknorrisjokes.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCategoryDetailUseCase @Inject constructor(private val repository: CategoryRepository) {
    suspend operator fun invoke(category: String): Flow<Resource<CategoryDetail>> = flow {
        repository.getCategoryDetail(category).collect { resource ->
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