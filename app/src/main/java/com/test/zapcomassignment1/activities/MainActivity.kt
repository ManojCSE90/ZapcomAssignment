/**
 * @copyright Copyright (c) 2024 Zapcom. All rights reserved.
 */
package com.test.zapcomassignment1.activities

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.zapcomassignment1.adapters.ProductCategoryAdapter
import com.test.zapcomassignment1.databinding.ActivityMainBinding
import com.test.zapcomassignment1.models.ApiResponse
import com.test.zapcomassignment1.models.ProductCategoryListItem
import com.test.zapcomassignment1.viewmodels.ProductViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * @author Manoj Kumar Yadav
 * This will show the list of categories of banner, free horizontal scrolling and split banner
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val productViewModel by viewModels<ProductViewModel>()
    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var adapter: ProductCategoryAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupView()
        registerObserver()
    }

    private fun setupView() {
        val linearLayoutManager = LinearLayoutManager(this)
        binding.rvProductCategories.layoutManager = linearLayoutManager
        binding.rvProductCategories.adapter = adapter
        adapter.setOnClick { item ->

            Toast.makeText(this@MainActivity,"Clicked: ${item.title}", Toast.LENGTH_SHORT).show()
        }

    }

    private fun registerObserver() {

        productViewModel.productList.observe(this) {
            Log.d(TAG, "API response: $it")
            when (it) {
                is ApiResponse.Error -> {
                    Log.d(TAG, "$it")
                    showErrorLayout(it)
                }

                ApiResponse.Loading -> {
                    Log.d(TAG, "$it")
                    binding.loader.visibility = View.VISIBLE
                    binding.errorLayout.visibility = View.GONE
                }

                is ApiResponse.Success -> {
                    Log.d(TAG, "$it")
                    binding.errorLayout.visibility = View.GONE
                    binding.loader.visibility = View.GONE
                    binding.rvProductCategories.visibility = View.VISIBLE

                    updateProductCategory(it.data)
                }
            }

        }
    }

    private fun showErrorLayout(error: ApiResponse.Error) {

        binding.errorLayout.visibility = View.VISIBLE
        binding.rvProductCategories.visibility = View.GONE
        binding.loader.visibility = View.GONE

        binding.errorLayout.setOnClickListener {
            productViewModel.fetchProduct()
        }

    }

    private fun updateProductCategory(data: List<ProductCategoryListItem>) {

        adapter.setData(data)

    }

    companion object{
        const val TAG = "MainActivity"
    }
}