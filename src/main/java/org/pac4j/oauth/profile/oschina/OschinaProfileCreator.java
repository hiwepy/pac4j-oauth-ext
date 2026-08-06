package org.pac4j.oauth.profile.oschina;

import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Token;
import com.github.scribejava.core.oauth.OAuthService;
import org.pac4j.core.client.IndirectClient;
import org.pac4j.oauth.config.OAuth20Configuration;
import org.pac4j.oauth.profile.creator.OAuth20ProfileCreator;

public class OschinaProfileCreator extends OAuth20ProfileCreator {

	public OschinaProfileCreator(OAuth20Configuration configuration, IndirectClient client) {
		super(configuration, client);
	}

	@Override
	protected void signRequest(OAuthService service, Token token, OAuthRequest request) {
		super.signRequest(service, token, request);
		// 指定返回值类型['json'|'jsonp'|'xml']
		request.addQuerystringParameter("dataType", "json");
	}

}
