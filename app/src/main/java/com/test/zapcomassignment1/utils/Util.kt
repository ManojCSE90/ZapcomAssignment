/**
 * @copyright Copyright (c) 2024 Zapcom. All rights reserved.
 */
package com.test.zapcomassignment1.utils

import android.content.res.Resources
import android.util.DisplayMetrics
/**
 * @author Manoj Kumar Yadav
 * This is the Utils class. Here you can define all common fun which is common
 */

object Util{

    fun getDeviceDimention():DisplayMetrics{
        val displayMetrics = Resources.getSystem().displayMetrics
        val width = displayMetrics.widthPixels // Screen width in pixels

        val height = displayMetrics.heightPixels // Screen height in pixels

        return displayMetrics
    }
}