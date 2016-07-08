package com.sushhift.comma.common

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
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
abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependencies()
        setContentView(getLayout())
        setupToolbar()
        initView(savedInstanceState)
    }

    /**
     * Inject your dependencies in case you need
     * */
    open fun injectDependencies() {

    }

    /**
     * Initialize your UI components. This method is executed within [ android.app.Activity#onCreate ]
     * */
    open fun initView(savedInstanceState: Bundle?) {

    }

    fun setupToolbar() {
        val toolbar : Toolbar? = findViewById(R.id.toolbar) as Toolbar?

        if(toolbar != null) {
            setSupportActionBar(toolbar)
        }
    }

    /**
     * @return Resource id of your layout file
     * */
    abstract fun getLayout() : Int
}