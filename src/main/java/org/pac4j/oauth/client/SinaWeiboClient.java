package org.pac4j.oauth.client;

import org.pac4j.core.context.WebContext;
import org.pac4j.oauth.profile.sina.SinaWeiboProfile;
import org.pac4j.oauth.profile.sina.SinaWeiboProfileCreator;
import org.pac4j.oauth.profile.sina.SinaWeiboProfileDefinition;

import com.github.scribejava.apis.SinaWeiboApi20;

public class SinaWeiboClient extends OAuth20Client<SinaWeiboProfile> {

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
        setConfiguration(configuration);
        defaultProfileCreator(new SinaWeiboProfileCreator(configuration));
        
        super.clientInit(context);
    }

}
