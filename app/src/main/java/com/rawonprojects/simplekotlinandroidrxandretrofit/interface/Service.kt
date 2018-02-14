package com.rawonprojects.simplekotlinandroidrxandretrofit.`interface`

import com.rawonprojects.simplekotlinandroidrxandretrofit.model.GitHub
import retrofit.http.GET
import retrofit.http.Path
import rx.Observable

/**
 * Created by rawon on 15/02/18.
 */

interface Service{
    @GET("users/{username}")
    fun getUser(@Path("username") username: String): Observable<GitHub>
}