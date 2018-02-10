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
package org.pac4j.oauth.profile.oschina;

import org.pac4j.oauth.config.OAuth20Configuration;
import org.pac4j.oauth.profile.creator.OAuth20ProfileCreator;

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;

public class OschinaProfileCreator extends OAuth20ProfileCreator<OschinaProfile> {

	public OschinaProfileCreator(OAuth20Configuration configuration) {
		super(configuration);
	}

	@Override
	protected void signRequest(OAuth2AccessToken accessToken, OAuthRequest request) {
		super.signRequest(accessToken, request);
		// 指定返回值类型['json'|'jsonp'|'xml']
 		request.addQuerystringParameter("dataType", "json");
	}
	
}
