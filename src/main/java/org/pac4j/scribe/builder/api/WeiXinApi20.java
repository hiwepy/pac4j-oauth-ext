package org.pac4j.scribe.builder.api;

import java.io.OutputStream;
import java.util.Map;

import org.pac4j.OAuth2Constants;
import org.pac4j.scribe.service.WeiXinOAuth20ServiceImpl;

import com.github.scribejava.core.builder.api.DefaultApi20;
import com.github.scribejava.core.extractors.OAuth2AccessTokenExtractor;
import com.github.scribejava.core.extractors.TokenExtractor;
import com.github.scribejava.core.httpclient.HttpClient;
import com.github.scribejava.core.httpclient.HttpClientConfig;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthConstants;
import com.github.scribejava.core.model.ParameterList;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;

/**
 * 用于定义获取微信返回的CODE与ACCESS_TOKEN
 */
public class WeiXinApi20 extends DefaultApi20 {

		
	public static final String AUTHORIZE_URL = "https://open.weixin.qq.com/connect/qrconnect";
	public static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token";

	protected WeiXinApi20() {
	}

	private static class InstanceHolder {
		private static final WeiXinApi20 INSTANCE = new WeiXinApi20();
	}

	public static WeiXinApi20 instance() {
		return InstanceHolder.INSTANCE;
	}

	@Override
	public Verb getAccessTokenVerb() {
		return Verb.GET;
	}

	@Override
	public String getAccessTokenEndpoint() {
		return ACCESS_TOKEN_URL;
	}

	@Override
	protected String getAuthorizationBaseUrl() {
		return AUTHORIZE_URL;
	}

	@Override
	public TokenExtractor<OAuth2AccessToken> getAccessTokenExtractor() {
		return OAuth2AccessTokenExtractor.instance();
	}

	@Override
	public OAuth20Service createService(String apiKey, String apiSecret, String callback, String scope,
			OutputStream debugStream, String state, String responseType, String userAgent,
			HttpClientConfig httpClientConfig, HttpClient httpClient) {
		return new WeiXinOAuth20ServiceImpl(this, apiKey, apiSecret, callback, scope, state, responseType, userAgent,
                httpClientConfig, httpClient);
	}
	
	@Override
	public String getAuthorizationUrl(String responseType, String apiKey, String callback, String scope, String state,
			Map<String, String> additionalParams) {
		
		final ParameterList parameters = new ParameterList(additionalParams);
        parameters.add(OAuthConstants.RESPONSE_TYPE, responseType);
        //parameters.add(OAuthConstants.CLIENT_ID, apiKey);
        // 此处微信采用appid
        parameters.add(OAuth2Constants.APPID, apiKey);
        
        if (callback != null) {
            parameters.add(OAuthConstants.REDIRECT_URI, callback);
        }

        if (scope != null) {
            parameters.add(OAuthConstants.SCOPE, scope);
        }

        if (state != null) {
            parameters.add(OAuthConstants.STATE, state);
        }

        return parameters.appendTo(getAuthorizationBaseUrl());
        
	}

}
