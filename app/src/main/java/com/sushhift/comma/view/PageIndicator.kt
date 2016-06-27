package com.sushhift.comma.view

import android.animation.Animator
import android.animation.AnimatorInflater
import android.animation.TimeInterpolator
import android.content.Context
import android.content.res.TypedArray
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.sushhift.comma.R
import kotlin.properties.Delegates

/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
 * Created by Sushhi <3 on 06/2016.
 *
 */
class PageIndicator : LinearLayout {

    private val DEFAULT_INDICATOR_WIDTH: Float = 5.0.toFloat()

    var indicatorWidth = -1
    var indicatorMargin = -1
    var indicatorHeight = -1
    var animatorResId = R.animator.scale_with_alpha
    var animatorReverseResId = 0
    var indicatorBackgroundResId = R.drawable.page_indicator
    var unselectedIndicatorBackgroundResId = R.drawable.page_indicator

    lateinit var animatorOut: Animator
    lateinit var animatorIn: Animator
    lateinit var immediateAnimatorIn: Animator
    lateinit var immediateAnimatorOut: Animator

    var viewPager: ViewPager by Delegates.observable(ViewPager(context)){
        prop, old, new ->
        configureViewPager()
    }


    var lastPosition = -1

    constructor(context: Context) : super(context){
        init(context, null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs){
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr){
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes){
        init(context, attrs)
    }

    fun init(context: Context, attrs: AttributeSet?){
        handleTypedArray(context, attrs)
        checkIndicatorConfig(context)
    }
    fun handleTypedArray(context: Context, attrs: AttributeSet?) {
        if (attrs == null) {
            return
        }

        val typedArray: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.PageIndicator)

        indicatorWidth = typedArray.getDimensionPixelSize(R.styleable.PageIndicator_ci_width, -1)
        indicatorHeight = typedArray.getDimensionPixelSize(R.styleable.PageIndicator_ci_height, -1)
        indicatorMargin = typedArray.getDimensionPixelSize(R.styleable.PageIndicator_ci_margin, -1)
        animatorResId = typedArray.getDimensionPixelSize(R.styleable.PageIndicator_ci_animator, 0)
        animatorReverseResId = typedArray.getDimensionPixelSize(R.styleable.PageIndicator_ci_animator_reverse, 0)
        indicatorBackgroundResId = typedArray.getDimensionPixelSize(R.styleable.PageIndicator_ci_drawable, 0)
        unselectedIndicatorBackgroundResId = typedArray.getDimensionPixelSize(R.styleable.PageIndicator_ci_drawable_unselected, 0)

        orientation = HORIZONTAL
        setGravity(Gravity.CENTER)

        val lp : ViewGroup.LayoutParams = ViewGroup.LayoutParams(context, attrs)
        layoutParams = lp

        typedArray.recycle()
    }


    private fun checkIndicatorConfig(context: Context) {
        indicatorWidth = if (indicatorWidth < 0) dipToPx(DEFAULT_INDICATOR_WIDTH) else indicatorWidth
        indicatorHeight = if (indicatorHeight < 0) dipToPx(DEFAULT_INDICATOR_WIDTH) else indicatorHeight
        indicatorMargin = if (indicatorMargin < 0) dipToPx(DEFAULT_INDICATOR_WIDTH) else indicatorMargin

        animatorResId = if (animatorResId == 0) R.animator.scale_with_alpha else animatorResId

        indicatorBackgroundResId = if(indicatorBackgroundResId == 0) R.drawable.page_indicator else indicatorBackgroundResId
        unselectedIndicatorBackgroundResId= if(unselectedIndicatorBackgroundResId == 0) R.drawable.page_indicator else unselectedIndicatorBackgroundResId

        animatorOut = createAnimatorOut(context)
        immediateAnimatorOut = createAnimatorOut(context)
        immediateAnimatorOut.duration = 0

        animatorIn = createAnimatorIn(context)
        immediateAnimatorIn = createAnimatorIn(context)
        immediateAnimatorIn.duration = 0

        layoutParams.height = indicatorHeight * 2
    }

    private fun createAnimatorIn(context: Context): Animator {
        return if (animatorReverseResId == 0) {
            animatorIn = AnimatorInflater.loadAnimator(context, animatorResId)
            animatorIn.interpolator = ReverseInterpolator()
            animatorIn
        } else {
            AnimatorInflater.loadAnimator(context, animatorReverseResId)
        }
    }

    private fun createAnimatorOut(context: Context): Animator {
        return AnimatorInflater.loadAnimator(context, animatorResId)
    }

    private fun configureViewPager(){
        if(viewPager.adapter != null){
            lastPosition = -1
            viewPager.addOnPageChangeListener(internalPageChangeListener)
            internalPageChangeListener.onPageSelected(viewPager.currentItem)

            createIndicators()
        }
    }

    private fun createIndicators() {
        removeAllViews()
        val count = viewPager.adapter?.count?: 0

        if (count <= 0)
            return

        val currentItem = viewPager.currentItem

        for(i in 0..count-1){
            if(currentItem == i)
                addIndicator(indicatorBackgroundResId, immediateAnimatorOut)
            else
                addIndicator(unselectedIndicatorBackgroundResId, immediateAnimatorIn)
        }
    }

    private fun addIndicator(backgroundResId: Int, animator: Animator) {
        if(animator.isRunning){
            animator.end()
            animator.cancel()
        }

        val indicator : View = View(context)
        indicator.setBackgroundResource(backgroundResId)
        addView(indicator, indicatorWidth, indicatorHeight)

        val lp : LayoutParams = indicator.layoutParams as LayoutParams
        lp.leftMargin = indicatorMargin
        lp.rightMargin = indicatorMargin
        indicator.layoutParams = lp

        animator.setTarget(indicator)
        animator.start()
    }

    val internalPageChangeListener : ViewPager.OnPageChangeListener = object : ViewPager.OnPageChangeListener{
        override fun onPageScrollStateChanged(state: Int) {
        }

        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        }

        override fun onPageSelected(position: Int) {
            if(viewPager.adapter == null || (viewPager.adapter as PagerAdapter).count <= 0)
                return

            if(animatorIn.isRunning){
                animatorIn.end()
                animatorIn.cancel()
            }

            if(animatorOut.isRunning){
                animatorOut.end()
                animatorOut.cancel()
            }

            val currentIndicator : View? = getChildAt(lastPosition)
            if(lastPosition >= 0 && currentIndicator != null ){
                currentIndicator.setBackgroundResource(unselectedIndicatorBackgroundResId)
                animatorIn.setTarget(currentIndicator)
                animatorIn.start()
            }

            val selectedIndicator : View? = getChildAt(position)
            if(selectedIndicator != null){
                selectedIndicator.setBackgroundResource(indicatorBackgroundResId)
                animatorOut.setTarget(selectedIndicator)
                animatorOut.start()
            }

            lastPosition = position
        }

    }
    class ReverseInterpolator : TimeInterpolator {
        override fun getInterpolation(input: Float): Float {
            return Math.abs(1.0f - input)
        }
    }

}

