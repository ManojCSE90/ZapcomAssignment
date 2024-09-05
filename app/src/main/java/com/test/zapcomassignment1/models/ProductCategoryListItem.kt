/**
 * @copyright Copyright (c) 2024 Zapcom. All rights reserved.
 */
package com.test.zapcomassignment1.models
/**
 * @author Manoj Kumar Yadav
 * This is the data class for holding category and list of product of this category
 */
data class ProductCategoryListItem(
    val items: List<Item>,
    val sectionType: String
)