package com.oneearth.shetkari

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.io.IOException

class PricesActivity : AppCompatActivity() {

    private val priceItems = ArrayList<PriceItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_price) // Use the new layout



        fetchDataFromAPI()



    }

    private fun fetchDataFromAPI() {
        val apiKey = "579b464db66ec23bdd000001be00aa7730004e43541239805360b0e5"
        val apiUrl = "https://api.data.gov.in/resource/9ef84268-d588-465a-a308-a864a43d0070?api-key=$apiKey&format=xml"

        val client = OkHttpClient()

        val request = Request.Builder()
            .url(apiUrl)
            .get()
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                response.body?.string()?.let { xmlData ->
                    parseXMLData(xmlData)
                }
            }
        })
    }

    private fun parseXMLData(xmlData: String) {
        try {
            val xmlPullParserFactory = XmlPullParserFactory.newInstance()
            val xmlPullParser = xmlPullParserFactory.newPullParser()

            xmlPullParser.setInput(xmlData.byteInputStream(), null)

            var eventType = xmlPullParser.eventType

            while (eventType != XmlPullParser.END_DOCUMENT) {
                when (eventType) {
                    XmlPullParser.START_TAG -> {
                        if (xmlPullParser.name == "item") {
                            val state = xmlPullParser.nextText()
                            val district = xmlPullParser.nextText()
                            val market = xmlPullParser.nextText()
                            val commodity = xmlPullParser.nextText()
                            val variety = xmlPullParser.nextText()
                            val grade = xmlPullParser.nextText()
                            val arrivalDate = xmlPullParser.nextText()
                            val minPrice = xmlPullParser.nextText().toDouble()
                            val maxPrice = xmlPullParser.nextText().toDouble()
                            val modalPrice = xmlPullParser.nextText().toDouble()

                            val priceItem = PriceItem(
                                state,
                                district,
                                market,
                                commodity,
                                variety,
                                grade,
                                arrivalDate,
                                minPrice,
                                maxPrice,
                                modalPrice
                            )
                            priceItems.add(priceItem)
                        }
                    }
                }
                eventType = xmlPullParser.next()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
