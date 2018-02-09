package org.pac4j.scribe.service;

import com.github.scribejava.core.builder.api.DefaultApi20;
import com.github.scribejava.core.model.AbstractRequest;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthConfig;
import com.github.scribejava.core.oauth.OAuth20Service;

/**
 */
public class OschinaOAuth20ServiceImpl extends OAuth20Service {

    public OschinaOAuth20ServiceImpl(DefaultApi20 api, OAuthConfig config) {
        super(api, config);
    }

    /**
     * 获取account_token的http请求参数添加
     */
    @Override
 	public void signRequest(OAuth2AccessToken accessToken, AbstractRequest request) {
 		request.addQuerystringParameter("dataType", "json");
 		super.signRequest(accessToken, request);
 	}
}
