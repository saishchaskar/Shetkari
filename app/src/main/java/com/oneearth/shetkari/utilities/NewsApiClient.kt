package com.oneearth.shetkari.utilities

import android.util.Log
import com.oneearth.shetkari.Models.Article
import com.oneearth.shetkari.Models.Source
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException

object NewsApiClient {


    fun fetchNews(callback: (List<Article>?, String?) -> Unit) {
        val url = "https://newsapi.org/v2/everything?q=agriculture crops india&apiKey=d7b8316ab7924a05818da03b403ad809"

        val request = Request.Builder()
            .url(url)
            .build()

        OkHttpClient().newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                callback(null, e.message)
            }

            override fun onResponse(call: Call, response: Response) {
                val responseData = response.body?.string()
                if (responseData != null) {
                    val articles = parseJson(responseData)
                    callback(articles, null)
                } else {
                    callback(null, "No response data")
                }
            }
        })
    }

    private fun parseJson(jsonData: String): List<Article> {
        val articles = mutableListOf<Article>()
        try {
            val jsonObject = JSONObject(jsonData)
            val jsonArray = jsonObject.getJSONArray("articles")
            for (i in 0 until jsonArray.length()) {
                val articleJson = jsonArray.getJSONObject(i)
                val sourceJson = articleJson.getJSONObject("source")
                val source = Source(sourceJson.optString("id"), sourceJson.getString("name"))
                val article = Article(
                    source,
                    articleJson.optString("author"),
                    articleJson.getString("title"),
                    articleJson.optString("description"),
                    articleJson.getString("url"),
                    articleJson.optString("urlToImage"),
                    articleJson.getString("publishedAt"),
                    articleJson.optString("content")
                )
                articles.add(article)
            }
        } catch (e: Exception) {
            Log.e("NewsApiClient", "Error parsing JSON", e)
        }
        return articles
    }
}
