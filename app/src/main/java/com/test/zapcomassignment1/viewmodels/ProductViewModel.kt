/**
 * @copyright Copyright (c) 2024 Zapcom. All rights reserved.
 */
package com.test.zapcomassignment1.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.zapcomassignment1.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
/**
 * @author Manoj Kumar Yadav
 * This is the view model class
 * This class is responsible for getting all product and expose data using Live data
 */
@HiltViewModel
class ProductViewModel @Inject constructor(private val repository: ProductRepository) :
    ViewModel() {

    val productList = repository.productList

    init {
       fetchProduct()
    }

    fun fetchProduct() {

        viewModelScope.launch(Dispatchers.IO) {
            repository.fetchProduct()
        }
    }

}