package org.pac4j.oauth.profile.baidu;

import org.pac4j.core.profile.AttributeLocation;
import org.pac4j.core.profile.converter.Converters;
import org.pac4j.oauth.config.OAuthConfiguration;
import org.pac4j.oauth.profile.JsonHelper;
import org.pac4j.oauth.profile.definition.OAuthProfileDefinition;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.scribejava.core.model.Token;

/**
 * http://developer.baidu.com/wiki/index.php?title=docs/oauth/rest/file_data_apis_list
 */
public class BaiduProfileDefinition extends OAuthProfileDefinition  {

	public static final String PROFILE_URL = "https://openapi.baidu.com/rest/2.0/passport/users/getInfo?access_token=%s";

	/** 当前登录用户的数字ID */
    public static final String USER_ID = "userid";
    /** 当前登录用户的用户名，值可能为空。 */
    public static final String USER_NAME = "username";
    /** 用户真实姓名，可能为空。 */
    public static final String REAL_NAME = "realname";
    /** 当前登录用户的头像
     *  头像的地址具体如下：
	 *	small image: http://tb.himg.baidu.com/sys/portraitn/item/{$portrait}
	 *	large image: http://tb.himg.baidu.com/sys/portrait/item/{$portrait}
     */
    public static final String PORTRAIT = "portrait";
    public static final String PORTRAIT_SMALL = "portrait_small_url";
 	public static final String PORTRAIT_LARGE = "portrait_large_url";
    public static final String PORTRAIT_SMALL_URL = "https://tb.himg.baidu.com/sys/portraitn/item/%s";
    public static final String PORTRAIT_LARGE_URL = "https://tb.himg.baidu.com/sys/portrait/item/%s";

    /** 自我简介，可能为空。 */
    public static final String USER_DETAIL = "userdetail";
    /** 生日，以yyyy-mm-dd格式显示。 */
    public static final String BIRTHDAY = "birthday";
    /** 婚姻状况  */
    public static final String MARRIAGE = "marriage";
    /** 用户性别, 性别。"1"表示男，"0"表示女 */
    public static final String SEX = "sex";
    /** 血型 */
    public static final String BLOOD = "blood";
    /** 体型 */
    public static final String FIGURE = "figure";
    /** 星座 */
    public static final String CONSTELLATION = "constellation";
    /** 学历 */
    public static final String EDUCATION = "education";
    /** 当前职业 */
    public static final String TRADE = "trade";
    /** 职位  */
    public static final String JOB = "job";

    public BaiduProfileDefinition() {
    	primary(USER_ID, Converters.STRING);
        primary(USER_NAME, Converters.STRING);
        primary(REAL_NAME, Converters.STRING);
        primary(PORTRAIT, Converters.STRING);
        primary(USER_DETAIL, Converters.STRING);
        primary(BIRTHDAY, Converters.STRING);
        primary(MARRIAGE, Converters.STRING);
        primary(SEX, new BaiduGenderConverter());
        primary(BLOOD, Converters.STRING);
        primary(FIGURE, Converters.STRING);
        primary(CONSTELLATION, Converters.STRING);
        primary(EDUCATION, Converters.STRING);
        primary(TRADE, Converters.STRING);
        primary(JOB, Converters.STRING);
        secondary(PORTRAIT_LARGE, Converters.URL);
        secondary(PORTRAIT_SMALL, Converters.URL);
    }

	@Override
	public String getProfileUrl(Token accessToken, OAuthConfiguration configuration) {
		return String.format(PROFILE_URL, accessToken.getRawResponse());
	}

	/**
	 *
	 * JSON数据格式
		{
		    "userid":"2097322476",
		    "username":"wl19871011",
		    "realname":"阳光",
		    "userdetail":"喜欢自由",
		    "birthday":"1987-01-01",
		    "marriage":"恋爱",
		    "sex":"男",
		    "blood":"O",
		    "constellation":"射手",
		    "figure":"小巧",
		    "education":"大学/专科",
		    "trade":"计算机/电子产品",
		    "job":"未知",
		    "birthday_year":"1987",
		    "birthday_month":"01",
		    "birthday_day":"01",
		}
	 * */
	@Override
	public BaiduProfile extractUserProfile(String body) {
		final BaiduProfile profile = new BaiduProfile();
        JsonNode json = JsonHelper.getFirstNode(body);
        if (json != null && JsonHelper.getElement(json, "error_code") == null) {
        	// 当前登录用户的数字ID
            profile.setId(JsonHelper.getElement(json, USER_ID).toString());
            // 主要属性
            for (final String attribute : getPrimaryAttributes()) {
				convertAndAdd(profile, AttributeLocation.PROFILE_ATTRIBUTE, attribute, JsonHelper.getElement(json, attribute));
			}
            // 次要属性
            convertAndAdd(profile, AttributeLocation.PROFILE_ATTRIBUTE, PORTRAIT_SMALL, String.format(PORTRAIT_SMALL_URL, JsonHelper.getElement(json, PORTRAIT).toString()) );
            convertAndAdd(profile, AttributeLocation.PROFILE_ATTRIBUTE, PORTRAIT_LARGE, String.format(PORTRAIT_LARGE_URL, JsonHelper.getElement(json, PORTRAIT).toString()) );
        } else {
            raiseProfileExtractionJsonError(body);
        }
        return profile;
	}
}
