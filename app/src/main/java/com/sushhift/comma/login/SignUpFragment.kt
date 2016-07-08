package com.sushhift.comma.login

import android.os.Bundle
import com.sushhift.comma.R
import com.sushhift.comma.common.BaseFragment

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

class SignUpFragment : BaseFragment(){

    companion object {
        fun newInstance(): SignUpFragment {
            val args: Bundle = Bundle()

            val fragment: SignUpFragment = SignUpFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun getFragmentLayout(): Int {
        return R.layout.fragment_sign_up
    }
}