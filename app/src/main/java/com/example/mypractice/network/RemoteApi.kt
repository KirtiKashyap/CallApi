package com.example.mypractice.network

import com.example.mypractice.model.UserData
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * The interface which provides methods to get result of webservices
 */
interface RemoteApi {
    /**
     * Get the list of the users from the API
     */
    @GET("users")
    fun getData(
        @Query("page") page: Int,
        @Query("pagesize") pagesize: Int,
        @Query("order") order: String,
        @Query("sort") sort: String,
        @Query("site") site: String
    ): Observable<UserData>
}