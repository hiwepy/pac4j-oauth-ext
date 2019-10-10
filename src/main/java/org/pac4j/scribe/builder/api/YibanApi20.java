/*
 * Copyright (c) 2018, vindell (https://github.com/vindell).
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

import java.io.OutputStream;
import java.util.Map;

import org.pac4j.oauth.client.YibanClient;
import org.pac4j.scribe.extractors.YibanJsonExtractor;
import org.pac4j.scribe.service.YibanService;

import com.github.scribejava.core.builder.api.DefaultApi20;
import com.github.scribejava.core.extractors.TokenExtractor;
import com.github.scribejava.core.httpclient.HttpClient;
import com.github.scribejava.core.httpclient.HttpClientConfig;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthConstants;
import com.github.scribejava.core.oauth.OAuth20Service;
import com.github.scribejava.core.oauth2.bearersignature.BearerSignature;
import com.github.scribejava.core.oauth2.bearersignature.BearerSignatureURIQueryParameter;
import com.github.scribejava.core.oauth2.clientauthentication.ClientAuthentication;
import com.github.scribejava.core.oauth2.clientauthentication.RequestBodyAuthenticationScheme;


/**
 * This class represents the OAuth API implementation for Yiban using OAuth protocol version 2.
 * It could be part of the Scribe library.
 * <p>More info at: <a href=
 * "https://open.yiban.cn/wiki/index.php?page=%E6%98%93%E7%8F%ADapi#1"
 * >OAuth2.0</a></p>
 * @author 		： <a href="https://github.com/vindell">wandl</a>
 */
public class YibanApi20 extends DefaultApi20 {

    public static final String APPID = "appid";
    public static final String SECRET = "secret";

    // https://open.yiban.cn/wiki/index.php?page=oauth/authorize
    private static final String AUTHORIZE_ENDPOINT = "https://openapi.yiban.cn/oauth/authorize";
    // https://open.yiban.cn/wiki/index.php?page=oauth/reset_token
    private static final String REFRESH_TOKEN_ENDPOINT = "https://openapi.yiban.cn/oauth/reset_token";
    // https://open.yiban.cn/wiki/index.php?page=oauth/access_token
    private static final String ACCESS_TOKEN_ENDPOINT = "https://openapi.yiban.cn/oauth/access_token";
   
    protected YibanApi20() {
	}

    private static class InstanceHolder {
        private static final YibanApi20 INSTANCE = new YibanApi20();
    }

    public static YibanApi20 instance() {
        return YibanApi20.InstanceHolder.INSTANCE;
    }

    @Override
    public String getAccessTokenEndpoint() {
        return ACCESS_TOKEN_ENDPOINT;
    }

    @Override
    public String getRefreshTokenEndpoint() {
        return REFRESH_TOKEN_ENDPOINT;
    }

    @Override
    protected String getAuthorizationBaseUrl() {
        return AUTHORIZE_ENDPOINT;
    }

    @Override
    public String getAuthorizationUrl(String responseType, String apiKey, String callback, String scope, String state,
            Map<String, String> additionalParams) {
        String authorizationUrl = super.getAuthorizationUrl(responseType, apiKey, callback, scope, state, additionalParams);
        authorizationUrl = authorizationUrl.replace(OAuthConstants.CLIENT_ID, APPID);
        if (scope != null && scope.contains(
        		YibanClient.YibanScope.SNSAPI_LOGIN.toString().toLowerCase())) {
            authorizationUrl = AUTHORIZE_ENDPOINT + authorizationUrl;
        } else {
            authorizationUrl = AUTHORIZE_ENDPOINT + authorizationUrl;
        }
        return authorizationUrl;
    }

    @Override
    public TokenExtractor<OAuth2AccessToken> getAccessTokenExtractor() {
        return YibanJsonExtractor.instance();
    }

    @Override
    public OAuth20Service createService(String apiKey, String apiSecret, String callback, String scope, OutputStream debugStream,
            String state, String responseType, String userAgent, HttpClientConfig httpClientConfig, HttpClient httpClient) {
        return new YibanService(this, apiKey, apiSecret, callback, scope, state, responseType, userAgent, httpClientConfig, httpClient);
    }

    @Override
    public BearerSignature getBearerSignature() {
        return BearerSignatureURIQueryParameter.instance();
    }

    @Override
    public ClientAuthentication getClientAuthentication() {
        return RequestBodyAuthenticationScheme.instance();
    }
}
