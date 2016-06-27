package com.sushhift.comma.login.intro

import android.os.Bundle
import android.support.v4.app.Fragment
import com.sushhift.comma.R
import com.sushhift.comma.common.ViewPagerActivity
import kotlinx.android.synthetic.main.activity_intro.*

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
 * Created by Susshi <3 on 06/2016.
 */
class IntroActivity : ViewPagerActivity() {

    override fun createFragments(): List<Fragment> {
        return listOf(IntroFragment.newInstance(), IntroFragment.newInstance())
    }

    override fun getPagerId(): Int {
        return R.id.introPager
    }

    override fun getLayout(): Int {
        return R.layout.activity_intro
    }

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        pagerIndicator.viewPager = pager
    }

}