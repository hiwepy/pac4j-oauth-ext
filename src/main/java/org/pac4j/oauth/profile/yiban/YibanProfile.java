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
package org.pac4j.oauth.profile.yiban;


import java.net.URI;
import java.util.Locale;

import org.pac4j.core.util.CommonHelper;
import org.pac4j.oauth.profile.OAuth20Profile;
import org.pac4j.oauth.profile.weibo.WeiboProfileDefinition;

/**
 * <p>This class is the user profile for Yiban (using OAuth protocol version 2) with appropriate getters.</p>
 * <p>It is returned by the {@link org.pac4j.oauth.client.YibanClient}.</p>
 *
 * @author 		： <a href="https://github.com/hiwepy">wandl</a>
 */
public class YibanProfile extends OAuth20Profile {

    private static final long serialVersionUID = -7486869356444327783L;

    @Override
    public String getFirstName() {
        return (String) getAttribute(YibanProfileDefinition.NAME);
    }

    @Override
    public String getDisplayName() {
        return (String) getAttribute(WeiboProfileDefinition.SCREEN_NAME);
    }

    @Override
    public String getUsername() {
        return (String) getAttribute(WeiboProfileDefinition.SCREEN_NAME);
    }

    @Override
    public Locale getLocale() {
        return (Locale) getAttribute(WeiboProfileDefinition.LANG);
    }

    @Override
    public URI getPictureUrl() {
        return (URI) getAttribute(WeiboProfileDefinition.AVATAR_HD);
    }

    @Override
    public URI getProfileUrl() {
        final URI attribute = (URI) getAttribute(WeiboProfileDefinition.PROFILE_URL);
        if (attribute.isAbsolute()) {
            return attribute;
        } else {
            return CommonHelper.asURI("http://weibo.com/" + attribute.toString());
        }
    }
}
