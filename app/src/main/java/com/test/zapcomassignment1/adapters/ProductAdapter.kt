/**
 * @copyright Copyright (c) 2024 Zapcom. All rights reserved.
 */
package com.test.zapcomassignment1.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.test.zapcomassignment1.databinding.ItemProductBinding
import com.test.zapcomassignment1.models.Item
import com.test.zapcomassignment1.viewholders.ProductItemViewHolder
/**
 * @author Manoj Kumar Yadav
 * This will show product list like watches, clothes, banner ad and split product
 */
class ProductAdapter(private var productCategory: String, private val picasso: Picasso, private val onClick: ((Item) -> Unit)?) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val productList: ArrayList<Item> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductItemViewHolder(binding, picasso, onClick)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (holder is ProductItemViewHolder){
            holder.bind(productList[position], productCategory)
        }
    }

    fun setData(data: List<Item>){
        productList.addAll(data)
        notifyDataSetChanged()
    }
}