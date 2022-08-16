package com.app.ml.searchProduct.data.api.network

import com.app.ml.searchProduct.data.models.ProductSearchResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {

    @GET("/sites/MLM/search?")
    suspend fun search(@Query("q") query: String): Response<ProductSearchResponseModel>
}