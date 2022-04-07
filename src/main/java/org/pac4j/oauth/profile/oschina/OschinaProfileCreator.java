package org.pac4j.oauth.profile.oschina;

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.oauth.OAuth20Service;
import org.pac4j.core.client.IndirectClient;
import org.pac4j.oauth.config.OAuth20Configuration;
import org.pac4j.oauth.profile.creator.OAuth20ProfileCreator;

import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Token;
import com.github.scribejava.core.oauth.OAuthService;

public class OschinaProfileCreator extends OAuth20ProfileCreator {

	public OschinaProfileCreator(OAuth20Configuration configuration, IndirectClient client) {
		super(configuration, client);
	}

	@Override
	protected void signRequest(final OAuth20Service service, final OAuth2AccessToken accessToken, final OAuthRequest request) {
		super.signRequest(service, accessToken, request);
		// 指定返回值类型['json'|'jsonp'|'xml']
		request.addQuerystringParameter("dataType", "json");
	}

}
