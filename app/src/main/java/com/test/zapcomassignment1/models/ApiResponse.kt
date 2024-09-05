/**
 * @copyright Copyright (c) 2024 Zapcom. All rights reserved.
 */
package com.test.zapcomassignment1.models
/**
 * @author Manoj kumar yadav
 * This is a generic class for handling API responses.
 */
sealed class ApiResponse<out T> {
    data class Success<out T>(val data: T) : ApiResponse<T>()
    data class Error(val message: String, val cause: Throwable? = null) : ApiResponse<Nothing>()
    data object Loading : ApiResponse<Nothing>()
}
