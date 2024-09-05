/**
 * @copyright Copyright (c) 2024 Zapcom. All rights reserved.
 */
package com.test.zapcomassignment1.network

import com.test.zapcomassignment1.models.ProductCategoryListItem
import retrofit2.Response
import retrofit2.http.GET
/**
 * @author Manoj Kumar Yadav
 * This is the class where all apis should be declared
 */
interface ProductApi {

    @GET("/b/5BEJ")
    suspend fun fetchProduct():Response<List<ProductCategoryListItem>>


    companion object {
        const val BASE_URL = "https://www.jsonkeeper.com"
        const val API_KEY_PARAM = "api_key"
    }
}