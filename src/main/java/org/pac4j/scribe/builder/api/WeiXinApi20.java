package org.pac4j.scribe.builder.api;

import org.pac4j.scribe.service.WeiXinOAuth20ServiceImpl;

import com.github.scribejava.core.builder.api.DefaultApi20;
import com.github.scribejava.core.extractors.OAuth2AccessTokenExtractor;
import com.github.scribejava.core.extractors.TokenExtractor;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthConfig;
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
	public OAuth20Service createService(OAuthConfig config) {
		return new WeiXinOAuth20ServiceImpl(this, config);
	}

}
