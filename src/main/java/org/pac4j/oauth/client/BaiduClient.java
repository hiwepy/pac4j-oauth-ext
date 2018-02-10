package org.pac4j.oauth.client;

import org.pac4j.core.context.WebContext;
import org.pac4j.oauth.profile.baidu.BaiduProfile;
import org.pac4j.oauth.profile.baidu.BaiduProfileDefinition;

import com.github.scribejava.apis.RenrenApi;

/**
 */
public class BaiduClient extends OAuth20Client<BaiduProfile> {

	public BaiduClient() {
	}

	public BaiduClient(final String key, final String secret) {
		setKey(key);
		setSecret(secret);
	}

	@Override
	protected void clientInit(final WebContext context) {

		configuration.setApi(RenrenApi.instance());
		configuration.setProfileDefinition(new BaiduProfileDefinition());
		setConfiguration(configuration);
		 
		super.clientInit(context);
	}

}
