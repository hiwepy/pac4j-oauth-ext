package org.pac4j.scribe.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.pac4j.OAuth2Constants;

import com.github.scribejava.core.builder.api.DefaultApi20;
import com.github.scribejava.core.exceptions.OAuthException;
import com.github.scribejava.core.model.AbstractRequest;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthConfig;
import com.github.scribejava.core.oauth.OAuth20Service;

/**
 * <p>http://wiki.connect.qq.com/%E4%BD%BF%E7%94%A8authorization_code%E8%8E%B7%E5%8F%96access_token</p>
 */
public class QQOAuth20ServiceImpl extends OAuth20Service {
	
	private static Pattern openIdPattern = Pattern.compile("\"openid\":\\s*\"(\\S*?)\"");
	
    public QQOAuth20ServiceImpl(DefaultApi20 api, OAuthConfig config) {
		super(api, config);
	}

	@Override
	public void signRequest(OAuth2AccessToken accessToken, AbstractRequest request) {
		String response = accessToken.getRawResponse();
		Matcher matcher = openIdPattern.matcher(response);
		if (matcher.find()) {
			request.addQuerystringParameter(OAuth2Constants.OPENID, matcher.group(1));
		} else {
			throw new OAuthException("接口返回数据 miss openid: " + response);
		}
		request.addQuerystringParameter("dataType", "json");
		super.signRequest(accessToken, request);
	}
      
}
