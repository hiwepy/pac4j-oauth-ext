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
package org.pac4j.scribe.builder.api;


import org.pac4j.scribe.service.OschinaOAuth20ServiceImpl;

import com.github.scribejava.core.builder.api.DefaultApi20;
import com.github.scribejava.core.extractors.OAuth2AccessTokenExtractor;
import com.github.scribejava.core.extractors.TokenExtractor;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthConfig;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;

public class OschinaApi20 extends DefaultApi20 {
	
	public static final String AUTHORIZE_URL = "http://www.oschina.net/action/oauth2/authorize";
	public static final String ACCESS_TOKEN_URL = "http://www.oschina.net/action/openapi/token";

	protected OschinaApi20() {
    }

    private static class InstanceHolder {
        private static final OschinaApi20 INSTANCE = new OschinaApi20();
    }

    public static OschinaApi20 instance() {
        return InstanceHolder.INSTANCE;
    }

    @Override
    public Verb getAccessTokenVerb() {
        return Verb.GET;
    }

    @Override
	protected String getAuthorizationBaseUrl() {
		return AUTHORIZE_URL;
	}
    
    @Override
    public String getAccessTokenEndpoint() {
        return ACCESS_TOKEN_URL;
    }

    @Override
    public TokenExtractor<OAuth2AccessToken> getAccessTokenExtractor() {
        return OAuth2AccessTokenExtractor.instance();
    }
    
    @Override
    public OAuth20Service createService(OAuthConfig config) {
        return new OschinaOAuth20ServiceImpl(this, config);
    }

	
}
