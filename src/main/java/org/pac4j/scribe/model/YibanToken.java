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
package org.pac4j.scribe.model;

import org.pac4j.core.util.CommonHelper;

import com.github.scribejava.core.model.OAuth2AccessToken;

/**
 * Yiban token extra.
 * <p>More info at: <a href="https://open.yiban.cn/wiki/index.php?page=oauth/access_token"> access_token</a></p>
 * @author 		： <a href="https://github.com/vindell">wandl</a>
 */
public class YibanToken extends OAuth2AccessToken {

    private static final long serialVersionUID = -4657457530761699382L;
    private String userid;
    private Integer expires;

    public YibanToken(String accessToken, String tokenType, Integer expiresIn,
                       String refreshToken, String scope, String rawResponse,
                       String userid) {
        super(accessToken, tokenType, expiresIn, refreshToken, scope, rawResponse);
        this.userid = userid;
        this.expires = expiresIn;
    }

    public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public Integer getExpires() {
		return expires;
	}

	public void setExpires(Integer expires) {
		this.expires = expires;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        YibanToken that = (YibanToken) o;

        if (userid != null ? !userid.equals(that.userid) : that.userid != null) return false;
        return expires != null ? expires.equals(that.expires) : that.expires == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (userid != null ? userid.hashCode() : 0);
        result = 31 * result + (expires != null ? expires.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return CommonHelper.toNiceString(YibanToken.class, "accessToken", getAccessToken(),
            "userid", userid);
    }
}
