package org.pac4j.oauth.client;

import java.util.ArrayList;
import java.util.List;

import org.pac4j.oauth.profile.baidu.BaiduProfileCreator;
import org.pac4j.oauth.profile.baidu.BaiduProfileDefinition;
import org.pac4j.scribe.builder.api.BaiduApi20;

/**
 */
public class BaiduClient extends OAuth20Client {

	public enum BaiduScope {
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

    protected List<BaiduScope> scopes;


    public BaiduClient() {
    }

    public BaiduClient(final String key, final String secret) {
        setKey(key);
        setSecret(secret);
    }

    @Override
    protected void internalInit(final boolean forceReinit) {
        super.internalInit(forceReinit);
        configuration.setApi(BaiduApi20.instance());
        configuration.setScope(getOAuthScope());
        configuration.setProfileDefinition(new BaiduProfileDefinition());
        configuration.setWithState(true);
        defaultProfileCreator(new BaiduProfileCreator(configuration, this));
    }

    protected String getOAuthScope() {
        StringBuilder builder = null;
        if (scopes == null || scopes.isEmpty()) {
            scopes = new ArrayList<>();
            scopes.add(BaiduScope.SNSAPI_BASE);
        }
        if (scopes != null) {
            for (BaiduScope value : scopes) {
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

    public List<BaiduScope> getScopes() {
        return scopes;
    }

    public void setScopes(List<BaiduScope> scopes) {
        this.scopes = scopes;
    }

    public void addScope(BaiduScope scopes) {
        if (this.scopes == null)
            this.scopes = new ArrayList<>();
        this.scopes.add(scopes);
    }

}
