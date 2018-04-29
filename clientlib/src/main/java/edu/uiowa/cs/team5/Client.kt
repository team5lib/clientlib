
package edu.uiowa.cs.team5

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody

import android.os.AsyncTask

class ClientController {
    inline fun <reified T> Gson.fromJson(json: String) = this.fromJson<T>(json, object : TypeToken<T>() {}.type)
    private var URL:String // Emulator IP: 10.0.2.2:8080
    private var loginURL:String
    private var createURL:String
    private val gson = Gson()
    private val client = OkHttpClient();
    private val JSON = MediaType.parse("application/json; charset=utf-8")

    constructor(serverIpAddress:String){
        URL = "http://"+serverIpAddress
        loginURL = URL + "/users/login"
        createURL = URL + "/users/create"
    }
    fun login(username: String, password: String): LoginResponse {
        val body = RequestBody.create(JSON, gson.toJson(LoginRequest(username, password)))
        return loginTask().execute(body).get()
    }

    fun create(username: String, password: String): CreateResponse {
        val body = RequestBody.create(JSON, gson.toJson(CreateRequest(username,password)))
        return createTask().execute(body).get()
    }

    private inner class loginTask : AsyncTask<RequestBody, Int, LoginResponse>() {
        override fun doInBackground(vararg re: RequestBody): LoginResponse {
            val request = Request.Builder()
                    .url(loginURL)
                    .post(re[0])
                    .build()
            val response = client.newCall(request).execute()
            return gson.fromJson<LoginResponse>(response.body()!!.string())
        }
    }

    private inner class createTask : AsyncTask<RequestBody, Int, CreateResponse>() {
        override fun doInBackground(vararg re: RequestBody): CreateResponse {
            val request = Request.Builder()
                    .url(createURL)
                    .post(re[0])
                    .build()
            val response = client.newCall(request).execute()
            return gson.fromJson<CreateResponse>(response.body()!!.string())
        }
    }
}
