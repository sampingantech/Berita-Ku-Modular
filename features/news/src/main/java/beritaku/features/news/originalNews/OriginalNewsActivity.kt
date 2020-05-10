package beritaku.features.news.originalNews

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModel
import beritaku.feature.news.R
import beritaku.features.news.base.BaseActivity
import beritaku.features.news.base.BaseErrorView
import beritaku.features.news.gone
import beritaku.features.news.visible
import kotlinx.android.synthetic.main.activity_original_news.*
import kotlinx.android.synthetic.main.layout_toolbar_back.*

class OriginalNewsActivity: BaseActivity<ViewModel?>() {

    companion object {
        const val EXTRA_URL = "EXTRA_URL"
        fun startActivity(context: Context, url: String){
            context.startActivity(Intent(context, OriginalNewsActivity::class.java)
                .putExtra(EXTRA_URL, url))
        }
    }

    override val mLayout: Int
        get() = R.layout.activity_original_news
    override val mViewModel: ViewModel?
        get() = null
    override val mToolbar: Toolbar?
        get() = toolbar
    override val mTitleToolbar: String?
        get() = null

    private lateinit var url: String
    private var isSuccessLoadUrl = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        url = intent?.getStringExtra(EXTRA_URL)?:""
        setupWebView(url)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_original_news, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.menu_open_browser -> {
                val webPage = Uri.parse(url)
                val intent = Intent(Intent.ACTION_VIEW, webPage)
                if (intent.resolveActivity(packageManager) != null) {
                    startActivity(intent)
                }
                true
            }
            else -> false
        }
    }

    private fun setupWebView(url: String){
        wv_original_news.loadUrl(url)
        wv_original_news.webViewClient = object: WebViewClient(){
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                isSuccessLoadUrl = true
                wv_original_news.gone()
                ev_original_news.showProgress()
                ev_original_news.visible()
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                if (isSuccessLoadUrl){
                    wv_original_news.visible()
                    ev_original_news.gone()
                    ev_original_news.endProgress()
                }
            }

            override fun onReceivedError(
                view: WebView?,
                request: WebResourceRequest?,
                error: WebResourceError?
            ) {
                isSuccessLoadUrl = false
                wv_original_news.gone()
                ev_original_news.showError(
                    errorMessage = getString(R.string.error_default),
                    errorType = BaseErrorView.ERROR_GENERAL
                )
                ev_original_news.setRetryClickListener {
                    view?.reload()
                }
                ev_original_news.visible()
            }
        }
    }
}
