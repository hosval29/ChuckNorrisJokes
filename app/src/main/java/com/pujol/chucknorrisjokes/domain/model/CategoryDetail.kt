package com.pujol.chucknorrisjokes.domain.model

data class CategoryDetail(
    val categories: List<String> = emptyList(),
    val createdAt: String = "",
    val iconUrl: String = "",
    val id: String = "",
    val value: String = ""
)