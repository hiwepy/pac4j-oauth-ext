package org.pac4j.scribe.service;

import com.github.scribejava.core.builder.api.DefaultApi20;
import com.github.scribejava.core.httpclient.HttpClient;
import com.github.scribejava.core.httpclient.HttpClientConfig;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.oauth.OAuth20Service;

/**
 * 用于添加获取ACCESS_TOKEN与用户信息添加参数并请求微信
 */
public class WeiXinOAuth20ServiceImpl extends OAuth20Service {

	public WeiXinOAuth20ServiceImpl(DefaultApi20 api, String apiKey, String apiSecret, String callback, String scope,
			String state, String responseType, String userAgent, HttpClientConfig httpClientConfig,
			HttpClient httpClient) {
		super(api, apiKey, apiSecret, callback, scope, state, responseType, userAgent, httpClientConfig, httpClient);
	}
	
	@Override
	protected OAuthRequest createAccessTokenRequest(String code, String pkceCodeVerifier) {
		// TODO Auto-generated method stub
		return super.createAccessTokenRequest(code, pkceCodeVerifier);
	}
	
	/*
	@Override
    protected <T extends AbstractRequest> T createAccessTokenRequest(String code, T request) {
    	request.addParameter(OAuth2Constants.APPID, getConfig().getApiKey());
    	return super.createAccessTokenRequest(code, request);
    }
    
    @Override
    protected <T extends AbstractRequest> T createRefreshTokenRequest(String refreshToken, T request) {
    	request.addParameter(OAuth2Constants.APPID, getConfig().getApiKey());
    	return super.createRefreshTokenRequest(refreshToken, request);
    }*/
    
   
    
}
