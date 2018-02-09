package org.pac4j.oauth.client;

import org.pac4j.core.context.WebContext;
import org.pac4j.oauth.profile.weixin.WeiXinProfile;
import org.pac4j.oauth.profile.weixin.WeiXinProfileDefinition;
import org.pac4j.scribe.builder.api.WeiXinApi20;

/**
 * 此类用于处理CAS与微信的OAUTH通信
 */
public class WeiXinClient extends OAuth20Client<WeiXinProfile> {

	public final static String DEFAULT_SCOPE = "test";

	private String scope = DEFAULT_SCOPE;
	private String logoutUrl;

	public WeiXinClient() {
	}

	public WeiXinClient(final String key, final String secret) {
		setKey(key);
		setSecret(secret);
	}

	@Override
	protected void clientInit(final WebContext context) {

		configuration.setApi(WeiXinApi20.instance());
		configuration.setProfileDefinition(new WeiXinProfileDefinition());
		configuration.setScope(this.scope);
		setConfiguration(configuration);

		super.clientInit(context);
	}

	public String getScope() {
		return this.scope;
	}

	public void setScope(final String scope) {
		this.scope = scope;
	}

	public String getLogoutUrl() {
		return logoutUrl;
	}

	public void setLogoutUrl(String logoutUrl) {
		this.logoutUrl = logoutUrl;
	}
	
}
