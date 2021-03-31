package org.pac4j.oauth.profile.baidu;

import org.pac4j.core.client.IndirectClient;
import org.pac4j.oauth.config.OAuth20Configuration;
import org.pac4j.oauth.profile.creator.OAuth20ProfileCreator;

public class BaiduProfileCreator extends OAuth20ProfileCreator {

	public BaiduProfileCreator(OAuth20Configuration configuration, IndirectClient client) {
		super(configuration, client);
	}

}
