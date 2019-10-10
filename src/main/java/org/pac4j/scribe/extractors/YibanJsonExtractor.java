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
package org.pac4j.scribe.extractors;

import java.util.regex.Pattern;

import org.pac4j.scribe.model.WechatToken;

import com.github.scribejava.core.extractors.OAuth2AccessTokenJsonExtractor;
import com.github.scribejava.core.model.OAuth2AccessToken;

/**
 * TODO
 * @author 		： <a href="https://github.com/vindell">wandl</a>
 */
public class YibanJsonExtractor extends OAuth2AccessTokenJsonExtractor {

	private static final Pattern OPENID_REGEX_PATTERN = Pattern.compile("\"userid\"\\s*:\\s*\"(\\S*?)\"");
    private static final Pattern EXPIRES_REGEX_PATTERN = Pattern.compile("\"expires\"\\s*:\\s*\"?(\\d*?)\"?\\D");

    protected YibanJsonExtractor() {
    }

    private static class InstanceHolder {

        private static final YibanJsonExtractor INSTANCE = new YibanJsonExtractor();
    }

    public static YibanJsonExtractor instance() {
        return YibanJsonExtractor.InstanceHolder.INSTANCE;
    }

    @Override
    protected OAuth2AccessToken createToken(String accessToken, String tokenType, Integer expiresIn,
                                            String refreshToken, String scope, String response) {
        String openid = extractParameter(response, OPENID_REGEX_PATTERN, true);
        String unionid = extractParameter(response, EXPIRES_REGEX_PATTERN, false);
        WechatToken token = new WechatToken(accessToken, tokenType, expiresIn, refreshToken, scope, response, openid, unionid);
        return token;
    }
}
