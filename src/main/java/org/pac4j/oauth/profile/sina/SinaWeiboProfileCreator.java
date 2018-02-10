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
package org.pac4j.oauth.profile.sina;

import java.io.IOException;

import org.pac4j.OAuth2Constants;
import org.pac4j.core.exception.HttpCommunicationException;
import org.pac4j.core.exception.TechnicalException;
import org.pac4j.oauth.config.OAuth20Configuration;
import org.pac4j.oauth.profile.JsonHelper;
import org.pac4j.oauth.profile.creator.OAuth20ProfileCreator;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;

/**
 * http://open.weibo.com/wiki/2/account/get_uid
 */
public class SinaWeiboProfileCreator extends OAuth20ProfileCreator<SinaWeiboProfile> {

	private static final String UID_URL = "https://api.weibo.com/2/account/get_uid.json";
	
	private String uid;
	
    public SinaWeiboProfileCreator(final OAuth20Configuration configuration) {
        super(configuration);
    }

    
    @Override
    protected void signRequest(final OAuth2AccessToken accessToken, final OAuthRequest request) {
    	super.signRequest(accessToken, request);
		try {
			
			//  使用Access Token来获取用户的UID
	        String uidUrl = String.format(UID_URL, accessToken);
	        
	        OAuthRequest openIdRequest = createOAuthRequest(uidUrl, Verb.GET);
	        Response response = openIdRequest.send();
	        int code = response.getCode();
	        
			String body = response.getBody();
			/*
			 返回结果
			 {
			     "uid":"3456676543"
			 }
			 错误返回值格式
			 {
			 	"request" : "/statuses/home_timeline.json",
			 	"error_code" : "20502",
			 	"error" : "Need you follow uid."
			 }
			 */
			JsonNode json = JsonHelper.getFirstNode(body);
			if (code != 200 || JsonHelper.getElement(json, "error_code") != null) {
	        	logger.error("Failed to get uid, code : " + code + " / body : " + body);
	            throw new HttpCommunicationException(code, body);
	        }
			uid = String.valueOf(JsonHelper.getElement(json, "uid"));
			request.addQuerystringParameter(OAuth2Constants.UID, uid);
	        
		} catch (IOException e) {
			throw new TechnicalException(e);
		}
    }
    
    @Override
    protected String sendRequestForData(OAuth2AccessToken accessToken, String dataUrl, Verb verb) {
        return JSONObject.parseObject(super.sendRequestForData(accessToken, dataUrl, verb)).put("uid", uid).toString();
    }

}
