/**
 *  Copyright 2014 ken.cai (http://www.shangpuyun.com)
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *	you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *	Unless required by applicable law or agreed to in writing, software
 *	distributed under the License is distributed on an "AS IS" BASIS,
 *	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *	See the License for the specific language governing permissions and
 *	limitations under the License.
 *
 */
package com.example.app.loading.example;

import android.content.Context;
import android.text.TextUtils;

import com.example.app.loading.avalidations.ValidationExecutor;

import java.util.regex.Pattern;


/**
 * @Description: 密码校验
 * @author ken.cai
 * @date 2014-11-21 下午9:43:25 
 * @version V1.0   
 * 
 */
public class PasswordValidation extends ValidationExecutor {

	@Override
	public boolean doValidate(Context context, String text) {

		String regex = "^[a-zA-Z][A-Za-z0-9]{7,11}$";
		boolean result = Pattern.compile(regex).matcher(text).find();
//		if (!result) {
//			Toast.makeText(context, "密码不正确", Toast.LENGTH_SHORT).show();
//			return false;
//		}
		if (TextUtils.isEmpty(text.toString())){
			return false;
		}
		return true;

	}

}