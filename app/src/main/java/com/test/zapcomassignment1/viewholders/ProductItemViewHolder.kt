/**
 * @copyright Copyright (c) 2024 Zapcom. All rights reserved.
 */
package com.test.zapcomassignment1.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.test.zapcomassignment1.R
import com.test.zapcomassignment1.databinding.ItemProductBinding
import com.test.zapcomassignment1.models.Item
import com.test.zapcomassignment1.utils.Util
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation
/**
 * @author Manoj Kumar Yadav
 * This is the view holder class
 * This class is responsible for displaying each product item
 */
class ProductItemViewHolder(
    private val binding: ItemProductBinding,
    private val picasso: Picasso,
    private val onClick: ((Item) -> Unit)?
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(product: Item, productCategory: String) {

        binding.productName.text = product.title

        val dimention = Util.getDeviceDimention()
        var imageWidth = binding.root.resources.getDimensionPixelSize(R.dimen.horizontal_scroll_image_size)
        var imageHeight = imageWidth

        when (productCategory) {

            "banner" -> {
                imageWidth = dimention.widthPixels- 45
                imageHeight = binding.root.resources.getDimensionPixelSize(R.dimen.banner_image_size)
            }

            "horizontalFreeScroll" -> {
                imageWidth = binding.root.resources.getDimensionPixelSize(R.dimen.horizontal_scroll_image_size)
                imageHeight = imageWidth
            }

            "splitBanner" -> {
                imageWidth = dimention.widthPixels / 2
                imageHeight = binding.root.resources.getDimensionPixelSize(R.dimen.banner_image_size)
            }
        }

        val param = binding.productImage.layoutParams
        param.width = imageWidth
        param.height = imageHeight
        binding.productImage.layoutParams = param
        binding.productImage.invalidate()

        val cornerRadius = binding.root.resources.getDimensionPixelSize(R.dimen.horizontaL_carousel_image_corner_radius)
        picasso.load(product.image)
            .resize(imageWidth, imageHeight)
            .centerCrop()
            .transform(RoundedCornersTransformation(cornerRadius, 0))
            .into(binding.productImage)

        binding.root.setOnClickListener {
            onClick?.let { click -> click(product) }
        }
    }
}