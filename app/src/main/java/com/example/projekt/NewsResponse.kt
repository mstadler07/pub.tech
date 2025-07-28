package com.example.pubtech_project

data class NewsResponse(
    val count: Int,
    val data: List<Article>
)

data class Article(
    val id: String,
    val title: String?,
    val teaserText: String,
    val createdAt: String,
    val headline: String?,
    val publicationDate: String,
    val shareLink: String?,
    val text: String?,
    val articleSource: String?,
    val articleType: String?,
    val isBreakingNews: Boolean,
    val images: List<ArticleImage> = emptyList(),
    val audio: List<MediaFile> = emptyList(),
    val video: List<MediaFile> = emptyList(),
    val tags: List<String> = emptyList()
)


data class ArticleImage(
    val author: String?,
    val description: String?,
    val url: String
)

data class MediaFile(
    val duration: Int?,
    val url: String
)
