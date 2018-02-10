package org.pac4j.oauth.client;

import org.pac4j.core.context.WebContext;
import org.pac4j.oauth.profile.oschina.OschinaProfile;
import org.pac4j.oauth.profile.oschina.OschinaProfileCreator;
import org.pac4j.oauth.profile.oschina.OschinaProfileDefinition;
import org.pac4j.scribe.builder.api.OschinaApi20;

/**
 */
public class OschinaClient extends OAuth20Client<OschinaProfile> {

	public OschinaClient() {
	}

	public OschinaClient(final String key, final String secret) {
		setKey(key);
		setSecret(secret);
	}

	@Override
	protected void clientInit(final WebContext context) {

		configuration.setApi(OschinaApi20.instance());
		configuration.setProfileDefinition(new OschinaProfileDefinition());
		setConfiguration(configuration);
		defaultProfileCreator(new OschinaProfileCreator(configuration));
		 
		super.clientInit(context);
	}

}
