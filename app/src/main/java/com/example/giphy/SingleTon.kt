package com.example.giphy

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley


class SingleTon private constructor(context: Context) {
    private var requestQueue: RequestQueue?
    private fun getRequestQueue(): RequestQueue? {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(ctx.getApplicationContext())
        }
        return requestQueue
    }

    fun <T> addToRequestQueue(req: Request<T>?) {
        getRequestQueue()!!.add<T>(req)
    }

    companion object {
        private var instance: SingleTon? = null
        private lateinit var ctx: Context
        fun getInstance(context: Context): SingleTon? {
            if (instance == null) {
                instance = SingleTon(context)
            }
            return instance
        }
    }

    init {
        ctx = context
        requestQueue = getRequestQueue()
    }
}