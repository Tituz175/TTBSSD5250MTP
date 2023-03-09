package com.example.tt_bssd5250_mtp

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.RawContacts.Data
import android.util.Log
import android.view.View
import android.widget.RelativeLayout
import androidx.appcompat.widget.LinearLayoutCompat
import okhttp3.*
import okio.IOException
import com.google.gson.Gson
import org.json.JSONArray
import org.json.JSONObject
import androidx.fragment.app.commit
import com.google.gson.JsonParser
import java.io.InputStream
import java.net.URL
import javax.net.ssl.HttpsURLConnection
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    private val apiKey = "b7b2f7e7525a40469ffea44020b95c45"
    private val apiUrl = "https://newsapi.org/v2/top-headlines?sources=cnn"

    private val requestURL = "$apiUrl&apiKey=$apiKey"
    private val client = OkHttpClient()
    private var apiResponse: List<MyData>? = null
    private var fid = 0
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
//        apiResponse = arrayOf()
        apiCall(requestURL)
//        createNoteFragments()


        val fragmentLinearLayout = LinearLayoutCompat(this).apply {
            id = View.generateViewId()
            fid = id
            orientation = LinearLayoutCompat.VERTICAL
            setBackgroundColor(Color.LTGRAY)
            layoutParams = LinearLayoutCompat.LayoutParams(
                LinearLayoutCompat.LayoutParams.MATCH_PARENT,
                LinearLayoutCompat.LayoutParams.MATCH_PARENT
            )

        }

        val relativeLayout = RelativeLayout(this).apply {
            setBackgroundColor(Color.WHITE)
            addView(fragmentLinearLayout)

        }
        setContentView(relativeLayout)

    }

    private fun apiCall(url: String) {
        thread(true) {
            val req = Request.Builder().url(requestURL).build()
            client.newCall(req).enqueue(object : Callback {
                override fun onResponse(call: Call, response: Response) {
                    val responseBody = response.body?.string()
                    val jsonObject = JSONObject(responseBody)
                }

                override fun onFailure(call: Call, e: IOException) {
//                        // Handle failure
                }
            })
        }
    }
    data class MyData(
        val id: Int,
        val name: String,
        val description: String
    )
}
