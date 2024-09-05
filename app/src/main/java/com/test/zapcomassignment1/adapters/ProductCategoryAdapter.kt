/**
 * @copyright Copyright (c) 2024 Zapcom. All rights reserved.
 */
package com.test.zapcomassignment1.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.test.zapcomassignment1.databinding.ItemProductCategoryBinding
import com.test.zapcomassignment1.models.Item
import com.test.zapcomassignment1.models.ProductCategoryListItem
import com.test.zapcomassignment1.viewholders.ProductCategoryViewHolder
import javax.inject.Inject

/**
 * @author Manoj Kumar Yadav
 * This is the category adapter class responsible for displaying item categories wise
 */
class ProductCategoryAdapter @Inject constructor(private val picasso: Picasso)  :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val productList: ArrayList<ProductCategoryListItem> = ArrayList()
    private var onClick: ((Item) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val binding =
            ItemProductCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductCategoryViewHolder(binding, onClick, picasso)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (holder is ProductCategoryViewHolder) {
            holder.bind(productList[position])
        }
    }

    fun setOnClick(onClick: ((item: Item) -> Unit)?) {
        this.onClick = onClick
    }

    fun setData(data: List<ProductCategoryListItem>){
        productList.addAll(data)
        notifyDataSetChanged()
    }
}