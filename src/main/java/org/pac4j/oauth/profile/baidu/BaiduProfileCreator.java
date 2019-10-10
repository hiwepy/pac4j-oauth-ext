package org.pac4j.oauth.profile.baidu;

import org.pac4j.core.client.IndirectClient;
import org.pac4j.oauth.config.OAuth20Configuration;
import org.pac4j.oauth.profile.creator.OAuth20ProfileCreator;

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.oauth.OAuth20Service;

public class BaiduProfileCreator extends OAuth20ProfileCreator<BaiduProfile> {

	public BaiduProfileCreator(OAuth20Configuration configuration, IndirectClient client) {
		super(configuration, client);
	}
	
	@Override
	protected void signRequest(OAuth20Service service, OAuth2AccessToken accessToken, OAuthRequest request) {
		super.signRequest(service, accessToken, request);
	}

}
