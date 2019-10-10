package org.pac4j.oauth.client;

import java.util.ArrayList;
import java.util.List;

import org.pac4j.oauth.profile.oschina.OschinaProfileCreator;
import org.pac4j.oauth.profile.oschina.OschinaProfileDefinition;
import org.pac4j.scribe.builder.api.OschinaApi20;

/**
 */
public class OschinaClient extends OAuth20Client {

	public enum OschinaScope {
        /**
         * Only for WeChat QRCode login. Get the nickname, avatar, and gender of the logged in user.
         */
        SNSAPI_LOGIN,
        /**
         * Exchange code for access_token, refresh_token, and authorized scope
         */
        SNSAPI_BASE,
        /**
         * Get user personal information
         */
        SNSAPI_USERINFO
    }

    protected List<OschinaScope> scopes;


    public OschinaClient() {
    }

    public OschinaClient(final String key, final String secret) {
        setKey(key);
        setSecret(secret);
    }

    @Override
    protected void clientInit() {
        configuration.setApi(OschinaApi20.instance());
        configuration.setScope(getOAuthScope());
        configuration.setProfileDefinition(new OschinaProfileDefinition());
        configuration.setWithState(true);
        defaultProfileCreator(new OschinaProfileCreator(configuration, this));
        super.clientInit();
    }

    protected String getOAuthScope() {
        StringBuilder builder = null;
        if (scopes == null || scopes.isEmpty()) {
            scopes = new ArrayList<>();
            scopes.add(OschinaScope.SNSAPI_BASE);
        }
        if (scopes != null) {
            for (OschinaScope value : scopes) {
                if (builder == null) {
                    builder = new StringBuilder();
                } else {
                    builder.append(",");
                }
                builder.append(value.toString().toLowerCase());
            }
        }
        return builder == null ? null : builder.toString();
    }

    public List<OschinaScope> getScopes() {
        return scopes;
    }

    public void setScopes(List<OschinaScope> scopes) {
        this.scopes = scopes;
    }

    public void addScope(OschinaScope scopes) {
        if (this.scopes == null)
            this.scopes = new ArrayList<>();
        this.scopes.add(scopes);
    }

}
