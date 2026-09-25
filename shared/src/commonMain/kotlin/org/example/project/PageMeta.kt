package org.example.project

data class PageMeta(
    val limit: Int,
    val next: String,
    val offset: Int,
    val previous: Any,
    val total_count: Int
)