package com.sushhift.comma.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.annotation.IntDef
import android.support.v4.app.Fragment
import com.sushhift.comma.R
import com.sushhift.comma.common.FragmentContainerActivity

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
 * Created by Sushhi <3 on 07/2016.
 */
class LoginActivity : FragmentContainerActivity(){

    companion object {
        const val SIGN_IN = 1L
        const val SIGN_UP = 2L
        const val KEY_LOGIN_MODE = "login_mode"

        fun intent(context: Context, @LoginMode loginMode: Long): Intent {
            val args: Bundle = Bundle()
            args.putLong(KEY_LOGIN_MODE, loginMode)

            val intent: Intent = Intent(context, LoginActivity::class.java)
            return intent
        }
    }

    override fun createFragment(): Fragment {
        val mode = intent.getLongExtra(KEY_LOGIN_MODE, SIGN_IN)
        return if(mode == SIGN_UP) SignUpFragment.newInstance() else SignInFragment.newInstance()
    }

    override fun getContainerId(): Int {
        return R.id.fragment_container
    }

    override fun getLayout(): Int {
        return R.layout.activity_fragment_container
    }

    @IntDef(LoginActivity.SIGN_IN, LoginActivity.SIGN_UP)
    annotation class LoginMode()
}