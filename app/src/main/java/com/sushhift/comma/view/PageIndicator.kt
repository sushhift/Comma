package com.sushhift.comma.view

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.widget.LinearLayout
import com.sushhift.comma.R

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
class PageIndicator : LinearLayout{

    var indicatorWidth : Int = -1
    var indicatorMargin = -1
    var indicatorHeight = -1
    var animatorResId = R.animator.scale_with_alpha
    var animatorReverseResId = 0
    var animatorBackgroundResId =

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

    fun handleTypedArray(context: Context, attrs: AttributeSet?){
        val typedArray : TypedArray? = context.obtainStyledAttributes(attrs, R.styleable.CirclePageIndicator)

        typedArray?.recycle()
    }
}