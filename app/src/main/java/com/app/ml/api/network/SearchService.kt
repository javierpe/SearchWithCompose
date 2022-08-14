package com.app.ml.api.network

import com.app.ml.data.models.search.ProductSearchResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {

    @GET("/sites/MLM/search?")
    suspend fun search(@Query("q") query: String): Response<ProductSearchResponseModel>
}