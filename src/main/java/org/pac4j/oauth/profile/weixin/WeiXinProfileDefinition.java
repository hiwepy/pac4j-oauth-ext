package org.pac4j.oauth.profile.weixin;

import org.pac4j.core.exception.HttpAction;
import org.pac4j.core.profile.converter.Converters;
import org.pac4j.core.profile.converter.GenderConverter;
import org.pac4j.oauth.config.OAuth20Configuration;
import org.pac4j.oauth.profile.definition.OAuth20ProfileDefinition;
import org.pac4j.oauth.profile.qq.QQProfile;

import com.github.scribejava.core.model.OAuth2AccessToken;

/**
 * 用于接收微信返回的用户信息
 */
public class WeiXinProfileDefinition extends OAuth20ProfileDefinition<WeiXinProfile>  { 
	
    public static final String OPEN_ID = "openid";
    public static final String NICK_NAME = "nickname";
    /** 用户性别,1为男性,2为女性 */
    public static final String SEX = "sex";
    public static final String COUNTRY = "country";
    public static final String PROVINCE = "province";
    public static final String CITY = "city";
    public static final String HEAD_IMG_URL = "headimgurl";
    public static final String PRIVILEGE = "privilege";
    public static final String UNION_ID = "unionid";
    // appended
    public static final String APP_NAME = "appName";
    public static final String SUID = "suid";

    public WeiXinProfileDefinition(){
        primary(OPEN_ID, Converters.STRING);
        primary(NICK_NAME, Converters.STRING);
        primary(SEX, Converters.INTEGER);
        primary(COUNTRY, Converters.STRING);
        primary(PROVINCE, Converters.STRING);
        primary(CITY, Converters.STRING);
        primary(HEAD_IMG_URL, Converters.STRING);
        primary(UNION_ID, Converters.STRING);
        primary(APP_NAME, Converters.STRING);
        primary(SUID, Converters.INTEGER);
        primary(SUID, new GenderConverter());
        
    }

	@Override
	public String getProfileUrl(OAuth2AccessToken accessToken, OAuth20Configuration configuration) {
		return null;
	}

	@Override
	public WeiXinProfile extractUserProfile(String body) throws HttpAction {
		return null;
	}
}
