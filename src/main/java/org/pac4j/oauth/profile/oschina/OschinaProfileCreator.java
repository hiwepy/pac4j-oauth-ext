package org.pac4j.oauth.profile.oschina;

import org.pac4j.core.client.IndirectClient;
import org.pac4j.oauth.config.OAuth20Configuration;
import org.pac4j.oauth.profile.creator.OAuth20ProfileCreator;

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.oauth.OAuth20Service;

public class OschinaProfileCreator extends OAuth20ProfileCreator<OschinaProfile> {

	public OschinaProfileCreator(OAuth20Configuration configuration, IndirectClient client) {
		super(configuration, client);
	}
	
	@Override
	protected void signRequest(OAuth20Service service, OAuth2AccessToken accessToken, OAuthRequest request) {
		super.signRequest(service, accessToken, request);
		// 指定返回值类型['json'|'jsonp'|'xml']
		request.addQuerystringParameter("dataType", "json");
	}

}
