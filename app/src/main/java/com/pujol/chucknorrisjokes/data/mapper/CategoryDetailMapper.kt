package com.pujol.chucknorrisjokes.data.mapper

import com.pujol.chucknorrisjokes.data.dto.CategoryDetailDto
import com.pujol.chucknorrisjokes.domain.model.CategoryDetail

fun CategoryDetailDto.toCategoryDetail(): CategoryDetail {
    return CategoryDetail(
        categories = categories,
        createdAt = createdAt,
        iconUrl = iconUrl,
        id = id,
        value = value
    )
}