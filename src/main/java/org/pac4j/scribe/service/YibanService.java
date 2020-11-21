/*
 * Copyright (c) 2018, hiwepy (https://github.com/hiwepy).
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
package org.pac4j.scribe.service;

import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.pac4j.scribe.builder.api.YibanApi20;

import com.github.scribejava.core.builder.api.DefaultApi20;
import com.github.scribejava.core.httpclient.HttpClient;
import com.github.scribejava.core.httpclient.HttpClientConfig;
import com.github.scribejava.core.model.OAuthAsyncRequestCallback;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.oauth.OAuth20Service;

/**
 * As of scribejava 5.3, the enumeration class ClientAuthenticationType does not support inheritance,
 * and can not complete the client authentication of Yiban.
 *
 * @author 		： <a href="https://github.com/hiwepy">wandl</a>
 */
public class YibanService extends OAuth20Service {
    
    private final String apiKey;
    private final String apiSecrect;

    /**
     * Default constructor
     *
     * @param api    OAuth2.0 api information
     * @param apiKey the API key
     * @param apiSecret the API secret
     * @param callback the callback URL
     * @param scope the scope
     * @param responseType the response type
     * @param debugStream the debug Stream
     * @param userAgent the user agent
     * @param httpClientConfig the HTTP client configuration
     * @param httpClient  the HTTP client
     * 
     */
    public YibanService(DefaultApi20 api, String apiKey, String apiSecret, String callback, String scope,
            String responseType, OutputStream debugStream, String userAgent, HttpClientConfig httpClientConfig,
            HttpClient httpClient) {
    	super(api, apiKey, apiSecret, callback, scope, responseType, debugStream, userAgent, httpClientConfig, httpClient);
        this.apiKey = apiKey;
        this.apiSecrect = apiSecret;
    }

    @Override
    public <R> Future<R> execute(OAuthRequest request, OAuthAsyncRequestCallback<R> callback,
                                 OAuthRequest.ResponseConverter<R> converter) {
        OAuthRequest authRequest = addClientAuthentication(request);
        return super.execute(authRequest, callback, converter);
    }

    @Override
    public Response execute(OAuthRequest request)
        throws InterruptedException, ExecutionException, IOException {
        OAuthRequest authRequest = addClientAuthentication(request);
        return super.execute(authRequest);
    }

    private OAuthRequest addClientAuthentication(OAuthRequest request) {
        request.addParameter(YibanApi20.APPID, this.apiKey);
        request.addParameter(YibanApi20.SECRET, this.apiSecrect);
        return request;
    }
}
