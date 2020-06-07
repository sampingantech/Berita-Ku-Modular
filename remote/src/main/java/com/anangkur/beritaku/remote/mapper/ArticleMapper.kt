package com.anangkur.beritaku.remote.mapper

import com.anangkur.beritaku.data.model.ArticleEntity
import com.anangkur.beritaku.remote.model.ArticleModel

class ArticleMapper: Mapper<ArticleModel, ArticleEntity> {

    companion object{
        private var INSTANCE: ArticleMapper? = null
        fun getInstance() = INSTANCE ?: ArticleMapper()
    }

    override fun mapFromRemote(type: ArticleModel): ArticleEntity {
        return ArticleEntity(
            id = type.id,
            title = type.title,
            author = type.author,
            category = type.category,
            content = type.content,
            description = type.description,
            publishedAt = type.publishedAt,
            url = type.url,
            urlToImage = type.urlToImage
        )
    }
}