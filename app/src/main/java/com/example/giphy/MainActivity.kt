package com.example.giphy

import DataAdapter
import android.app.DownloadManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Adapter
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity(),DataAdapter.OnitemClickListener {

    private val recyclerView:RecyclerView
       get()= findViewById(R.id.gif_recycler)

    private lateinit var modelList:ArrayList<DataModel>
    private lateinit var adapter:DataAdapter

    private val api_key= "Kx9qJ5zokJFEMaTzFEYVWeV3aWEWxNxT"
    private val base_url="https://api.giphy.com/v1/gifs/trending?api_key="
    val dataUrl= base_url+api_key


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager= GridLayoutManager(this,2)
        recyclerView.setHasFixedSize(true)

        modelList= ArrayList<DataModel>()
        getData()

    }

    private fun getData(){

        val objectReq= JsonObjectRequest(Request.Method.GET,dataUrl,null, {

            try {

                val dataArray= it.getJSONArray("data")

                for(i in 0..(dataArray.length()-1)){
                    var obj= dataArray.getJSONObject(i)

                    var obj1= obj.getJSONObject("images")
                    var obj2= obj1.getJSONObject("downsized_medium")

                    var sourceUrl= obj2.getString("url")
                    modelList.add(DataModel(sourceUrl))
                }

                adapter = DataAdapter(this,modelList)
                recyclerView.adapter= adapter
                adapter.notifyDataSetChanged()

            }catch (e:Exception){
                e.printStackTrace()
            }
        }, {
            Toast.makeText(this,"Error: ${it.localizedMessage}",Toast.LENGTH_LONG).show()
        })


        // Add data to Request Queue
        SingleTon.getInstance(this)?.addToRequestQueue(objectReq)
    }

    override fun onItemClick(pos: Int) {
       // TODO("Not yet implemented")
    }
}