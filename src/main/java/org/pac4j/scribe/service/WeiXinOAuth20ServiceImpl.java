package org.pac4j.scribe.service;

import org.pac4j.OAuth2Constants;

import com.github.scribejava.core.builder.api.DefaultApi20;
import com.github.scribejava.core.model.AbstractRequest;
import com.github.scribejava.core.model.OAuthConfig;
import com.github.scribejava.core.oauth.OAuth20Service;

/**
 * 用于添加获取ACCESS_TOKEN与用户信息添加参数并请求微信
 */
public class WeiXinOAuth20ServiceImpl extends OAuth20Service {

    public WeiXinOAuth20ServiceImpl(DefaultApi20 api, OAuthConfig config) {
        super(api, config);
    }

    @Override
    protected <T extends AbstractRequest> T createAccessTokenRequest(String code, T request) {
    	request.addParameter(OAuth2Constants.APPID, getConfig().getApiKey());
    	return super.createAccessTokenRequest(code, request);
    }
    
    @Override
    protected <T extends AbstractRequest> T createRefreshTokenRequest(String refreshToken, T request) {
    	request.addParameter(OAuth2Constants.APPID, getConfig().getApiKey());
    	return super.createRefreshTokenRequest(refreshToken, request);
    }
   
    
}
