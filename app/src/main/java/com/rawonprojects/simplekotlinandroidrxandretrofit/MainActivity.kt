package com.rawonprojects.simplekotlinandroidrxandretrofit

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.gson.GsonBuilder
import com.rawonprojects.simplekotlinandroidrxandretrofit.`interface`.Service
import com.rawonprojects.simplekotlinandroidrxandretrofit.model.GitHub
import retrofit.GsonConverterFactory
import retrofit.Retrofit
import retrofit.RxJavaCallAdapterFactory
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //inisialisasi gson
        val gson = GsonBuilder().create()

        //inisialisasi retrofit
        val retrofit: Retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl("https://api.github.com/")
                .build()

        val service: Service = retrofit.create(Service:: class.java)

        //get data form github by username
        service.getUser("agungwrdn")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            user -> getData(user)
                        },
                        {
                            error ->
                            Log.e("Error",error.message)
                        }
                )
    }

    private fun getData(user: GitHub?) {
        val image = findViewById<ImageView>(R.id.image)
        val username = findViewById<TextView>(R.id.username)
        val company = findViewById<TextView>(R.id.company)
        Glide.with(this).load(user?.avatarUrl).into(image)
        username.text = user?.name
        company.text = user?.company
    }
}
