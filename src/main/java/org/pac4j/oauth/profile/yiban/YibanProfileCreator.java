/*
 * Copyright (c) 2018, vindell (https://github.com/vindell).
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

import java.util.Optional;

import org.pac4j.core.client.IndirectClient;
import org.pac4j.core.context.WebContext;
import org.pac4j.core.profile.UserProfile;
import org.pac4j.oauth.config.OAuth20Configuration;
import org.pac4j.oauth.profile.creator.OAuth20ProfileCreator;
import org.pac4j.scribe.model.YibanToken;

import com.github.scribejava.core.model.OAuth2AccessToken;

/**
 * Specific profile creator for Yiban.
 *
 *  @author 		： <a href="https://github.com/vindell">wandl</a>
 */
public class YibanProfileCreator extends OAuth20ProfileCreator<YibanProfile> {
	
    public YibanProfileCreator(OAuth20Configuration configuration,
                                IndirectClient client) {
        super(configuration, client);
    }

    @Override
    protected Optional<UserProfile> retrieveUserProfileFromToken(WebContext context,
                                                                 OAuth2AccessToken accessToken) {
        final YibanToken token = (YibanToken) accessToken;
        final Optional<UserProfile> profile = super.retrieveUserProfileFromToken(context, token);
        ((YibanProfile) profile.get()).setId(token.getUserid());
        return profile;
    }
}
