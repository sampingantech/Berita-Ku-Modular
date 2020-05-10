package beritaku.features.news.injection

import android.content.Context
import com.anangkur.beritaku.data.ArticleDataRepository
import com.anangkur.beritaku.domain.features.news.GetArticles
import com.anangkur.beritaku.local.LocalRepository
import com.anangkur.beritaku.remote.RemoteRepository

object Injection{
    fun provideGetArticle(context: Context) = GetArticles.getInstance(
        ArticleDataRepository.getInstance(
            LocalRepository.getInstance(context), RemoteRepository.getInstance()
        ))
}