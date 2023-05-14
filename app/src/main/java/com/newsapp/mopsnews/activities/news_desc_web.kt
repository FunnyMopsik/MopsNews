import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import com.newsapp.mopsnews.R
import com.newsapp.mopsnews.activities.ShowNews

private  lateinit var myWebView:WebView
private lateinit var button: Button

class news_desc_web : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_desc_web)
        myWebView= findViewById(R.id.webview)
        myWebView.setWebViewClient(WebViewClient())
        myWebView.settings.javaScriptEnabled = true
        val url=intent.getStringExtra("url")
        Log.i("new url",url.toString())
        myWebView.loadUrl(url.toString())

        button= findViewById(R.id.back)
        button.setOnClickListener {
            val intent = Intent(this@news_desc_web, ShowNews::class.java)

            startActivity(intent)
        }

    }
}