/*
 * Copyright (c) 2010-2020, vindell (https://github.com/vindell).
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
package org.pac4j.oauth.profile.qq;

import org.pac4j.core.context.WebContext;
import org.pac4j.core.logout.GoogleLogoutActionBuilder;
import org.pac4j.core.logout.LogoutActionBuilder;
import org.pac4j.core.profile.CommonProfile;
import org.pac4j.core.redirect.RedirectAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QQLogoutActionBuilder<U extends CommonProfile> implements LogoutActionBuilder<U> {

    private static final Logger logger = LoggerFactory.getLogger(GoogleLogoutActionBuilder.class);

    private final String redirectUrl;
    
    public QQLogoutActionBuilder(String redirectUrl) {
    	this.redirectUrl = redirectUrl;
    }
    
    @Override
    public RedirectAction getLogoutAction(final WebContext context, final U currentProfile, final String targetUrl) {
        logger.debug("redirectUrl: {}", redirectUrl);
        return RedirectAction.redirect(redirectUrl);
    }


}
