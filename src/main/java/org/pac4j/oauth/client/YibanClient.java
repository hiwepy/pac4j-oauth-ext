/*
 * Copyright (c) 2018, hiwepy (https://github.com/hiwepy).
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.pac4j.oauth.client;

import java.util.ArrayList;
import java.util.List;

import org.pac4j.oauth.profile.yiban.YibanProfile;
import org.pac4j.oauth.profile.yiban.YibanProfileCreator;
import org.pac4j.oauth.profile.yiban.YibanProfileDefinition;
import org.pac4j.scribe.builder.api.YibanApi20;

/**
 * <p>This class is the OAuth client to authenticate users in Tencent Yiban.</p>
 * <p>It returns a {@link YibanProfile}.</p>
 * <p>More info at: <a href=
 * "https://open.weixin.qq.com/cgi-bin/showdocument?action=dir_list&t=resource/res_list&verify=1&id=open1419316505&token=&lang=zh_CN">
 * WeChat login development guide</a></p>
 *
 * @author 		： <a href="https://github.com/hiwepy">wandl</a>
 */
public class YibanClient extends OAuth20Client {

    public enum YibanScope {
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

    protected List<YibanScope> scopes;


    public YibanClient() {
    }

    public YibanClient(final String key, final String secret) {
        setKey(key);
        setSecret(secret);
    }

    @Override
    protected void clientInit() {
        super.clientInit();
        configuration.setApi(YibanApi20.instance());
        configuration.setScope(getOAuthScope());
        configuration.setProfileDefinition(new YibanProfileDefinition());
        configuration.setWithState(true);
        defaultProfileCreator(new YibanProfileCreator(configuration, this));
    }

    protected String getOAuthScope() {
        StringBuilder builder = null;
        if (scopes == null || scopes.isEmpty()) {
            scopes = new ArrayList<>();
            scopes.add(YibanScope.SNSAPI_BASE);
        }
        if (scopes != null) {
            for (YibanScope value : scopes) {
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

    public List<YibanScope> getScopes() {
        return scopes;
    }

    public void setScopes(List<YibanScope> scopes) {
        this.scopes = scopes;
    }

    public void addScope(YibanScope scopes) {
        if (this.scopes == null)
            this.scopes = new ArrayList<>();
        this.scopes.add(scopes);
    }
}
