package org.pac4j.oauth.client;

import org.pac4j.core.context.WebContext;
import org.pac4j.oauth.profile.sina.SinaWeboProfile;
import org.pac4j.oauth.profile.sina.SinaWeiboProfileDefinition;

import com.github.scribejava.apis.SinaWeiboApi20;

public class SinaWeiboClient extends OAuth20Client<SinaWeboProfile> {

    public final static String DEFAULT_SCOPE = "test";

    private String scope = DEFAULT_SCOPE;
    private String logoutUrl;
    
    public SinaWeiboClient() {
    }

    public SinaWeiboClient(final String key, final String secret) {
        setKey(key);
        setSecret(secret);
    }

    @Override
    protected void clientInit(final WebContext context) {
       
    	configuration.setApi(SinaWeiboApi20.instance());
        configuration.setProfileDefinition(new SinaWeiboProfileDefinition());
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
