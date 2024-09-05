/**
 * @copyright Copyright (c) 2024 Zapcom. All rights reserved.
 */
package com.test.zapcomassignment1.viewholders

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.test.zapcomassignment1.adapters.ProductAdapter
import com.test.zapcomassignment1.databinding.ItemProductCategoryBinding
import com.test.zapcomassignment1.models.Item
import com.test.zapcomassignment1.models.ProductCategoryListItem
/**
 * @author Manoj Kumar Yadav
 * This is the view holder class
 * This class is responsible for displaying category items.
 */
class ProductCategoryViewHolder(
    private val binding: ItemProductCategoryBinding,
    private val onClick: ((Item) -> Unit)?,
    private val picasso: Picasso
) : RecyclerView.ViewHolder(binding.root) {


    fun bind(product: ProductCategoryListItem) {
        binding.title.text = product.sectionType.replaceFirstChar {
            it.uppercase()
        }

        var adapter = ProductAdapter(
            productCategory = product.sectionType,
            picasso = picasso,
            onClick = onClick
        )

        val linearLayoutManager =
            LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, false)
        binding.rvProduct.layoutManager = linearLayoutManager
        binding.rvProduct.adapter = adapter
        adapter.setData(product.items)
    }
}