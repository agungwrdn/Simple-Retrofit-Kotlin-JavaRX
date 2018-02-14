package com.rawonprojects.simplekotlinandroidrxandretrofit.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by rawon on 15/02/18.
 */

open class GitHub{
    @SerializedName("avatar_url")
    @Expose
    open var avatarUrl: String? = null

    @SerializedName("name")
    @Expose
    open var name : String? = null

    @SerializedName("company")
    @Expose
    open var company : String? = null
}