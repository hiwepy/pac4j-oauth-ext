/*
 * Copyright (c) 2010-2020, vindell (https://github.com/vindell).
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.pac4j.oauth.profile.weixin;

import org.pac4j.oauth.config.OAuth20Configuration;
import org.pac4j.oauth.profile.creator.OAuth20ProfileCreator;

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.Verb;

/**
 */
public class WeiXinProfileCreator extends OAuth20ProfileCreator<WeiXinProfile> {

    public WeiXinProfileCreator(final OAuth20Configuration configuration) {
        super(configuration);
    }
    
    @Override
    protected String sendRequestForData(OAuth2AccessToken accessToken, String dataUrl, Verb verb) {
    	// 当且仅当该网站应用已获得该用户的userinfo授权时，才会出现该字段。
		//String unionid = accessToken.getParameter(OAuth2Constants.UNIONID);
		// 授权用户唯一标识
		//String openid = accessToken.getParameter(OAuth2Constants.OPENID);
        return super.sendRequestForData(accessToken, dataUrl, verb);
    }

}
