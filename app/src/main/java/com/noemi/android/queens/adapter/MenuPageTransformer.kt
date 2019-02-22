package com.noemi.android.queens.adapter

import android.support.v4.view.ViewPager
import android.view.View


private const val MIN_SCALE = 0.85F
private const val MIN_ALPHA = 0.6F

class MenuPageTransformer : ViewPager.PageTransformer {

    override fun transformPage(view: View, position: Float) {

        view.apply {
            val pageWidth = view.width
            val pageHeight = view.height

            when{
                position < -1 -> {
                    alpha = 0f
                }

                position <=1 -> {

                    val scaleFactor = Math.max(MIN_SCALE, 1- Math.abs(position))
                    val vertMargin = pageHeight * ( 1 - scaleFactor) /2
                    val horzMargin = pageWidth * ( 1 - scaleFactor) /2

                    translationX = if (position < 0) {
                        horzMargin - vertMargin / 2
                    } else {
                        horzMargin + vertMargin / 2
                    }

                    scaleX = scaleFactor
                    scaleY = scaleFactor

                    alpha = (MIN_ALPHA +
                            (((scaleFactor - MIN_SCALE) / (1 - MIN_SCALE)) * (1 - MIN_ALPHA)))

                }

                else -> {
                    alpha = 0f
                }
            }
        }

    }
}