package org.pac4j.oauth.profile.qq;

import org.pac4j.core.exception.HttpAction;
import org.pac4j.core.profile.converter.Converters;
import org.pac4j.oauth.config.OAuth20Configuration;
import org.pac4j.oauth.profile.definition.OAuth20ProfileDefinition;

import com.github.scribejava.core.model.OAuth2AccessToken;

/**
 */
public class QQProfileDefinition extends OAuth20ProfileDefinition<QQProfile>  {

    public static final String OPEN_ID = "openid";
    public static final String NICK_NAME = "nickname";
    /**
     * 用户性别,1为男性,2为女性
     */
    public static final String SEX = "sex";
    public static final String COUNTRY = "country";
    public static final String PROVINCE = "province";
    public static final String CITY = "city";
    public static final String HEAD_IMG_URL = "headimgurl";
    public static final String PRIVILEGE = "privilege";

    public QQProfileDefinition() {
    	primary(OPEN_ID, Converters.STRING);
        primary(NICK_NAME, Converters.STRING);
        primary(SEX, Converters.INTEGER);
        primary(COUNTRY, Converters.STRING);
        primary(PROVINCE, Converters.STRING);
        primary(CITY, Converters.STRING);
        primary(HEAD_IMG_URL, Converters.STRING);
        primary(PRIVILEGE, Converters.STRING);
    }

	@Override
	public String getProfileUrl(OAuth2AccessToken accessToken, OAuth20Configuration configuration) {
		return null;
	}

	@Override
	public QQProfile extractUserProfile(String body) throws HttpAction {
		
		
			
		
		return null;
	}
}