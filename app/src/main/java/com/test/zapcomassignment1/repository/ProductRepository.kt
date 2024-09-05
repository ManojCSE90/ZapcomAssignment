package com.test.zapcomassignment1.repository

/**
 * @copyright Copyright (c) 2024 Zapcom. All rights reserved.
 */
import android.content.Context
import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.test.zapcomassignment1.R
import com.test.zapcomassignment1.models.ApiResponse
import com.test.zapcomassignment1.models.ProductCategoryListItem
import com.test.zapcomassignment1.network.ProductApi
import com.test.zapcomassignment1.utils.NetworkUtil
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.delay
import javax.inject.Inject
/**
 * @author Manoj Kumar Yadav
 * This is the repository class responsible for providing data from API or from local database
 */
class ProductRepository @Inject constructor(
    private val productApi: ProductApi,
    private val resources: Resources,
    @ApplicationContext private val context: Context
) {

    private val _productList = MutableLiveData<ApiResponse<List<ProductCategoryListItem>>>()
    val productList: LiveData<ApiResponse<List<ProductCategoryListItem>>> = _productList

    suspend fun fetchProduct() {

        try {

            //checking internet
            if (NetworkUtil.isInternetAvailable(context).not()){
                _productList.postValue(ApiResponse.Error(resources.getString(R.string.error_msg)))
                return
            }

            _productList.postValue(ApiResponse.Loading)
            val response = productApi.fetchProduct()

            if (response.isSuccessful) {

                if (response.body() != null) {
                    val list = response.body()
                    _productList.postValue(ApiResponse.Success(list!!))
                } else {
                    _productList.postValue(ApiResponse.Error(resources.getString(R.string.error_msg)))
                }
            } else {
                _productList.postValue(ApiResponse.Error(resources.getString(R.string.error_msg)))
            }

        } catch (e: Exception) {
            val msg = e.message ?: resources.getString(R.string.error_msg)
            _productList.postValue(ApiResponse.Error(msg))
        }
    }
}