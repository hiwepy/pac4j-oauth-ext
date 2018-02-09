/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package org.pac4j.oauth.profile.sina;

import org.pac4j.core.exception.HttpAction;
import org.pac4j.core.profile.converter.Converters;
import org.pac4j.oauth.config.OAuth20Configuration;
import org.pac4j.oauth.profile.JsonHelper;
import org.pac4j.oauth.profile.definition.OAuth20ProfileDefinition;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.scribejava.core.model.OAuth2AccessToken;

/**
 * http://open.weibo.com/wiki/2/statuses/user_timeline 
 */
public class SinaWeiboProfileDefinition extends OAuth20ProfileDefinition<SinaWeboProfile> {

	private final static String PROFILE_URL = "https://api.weibo.com/2/statuses/user_timeline.json?access_token=%s";
	
	// 微博创建时间 
	public static final String CREATED_AT = "created_at";
	// 微博MID
	public static final String MID = "mid";
	// 字符串型的微博ID 
	public static final String IDSTR = "idstr";
	
	public static final String TYPE = "type";
	public static final String BLOG = "blog";
	public static final String URL = "url";
	public static final String PUBLIC_GISTS = "public_gists";
	public static final String FOLLOWING = "following";
	public static final String PRIVATE_GISTS = "private_gists";
	public static final String PUBLIC_REPOS = "public_repos";
	public static final String GRAVATAR_ID = "gravatar_id";
	public static final String AVATAR_URL = "avatar_url";
	public static final String FOLLOWERS = "followers";
	public static final String LOGIN = "login";
	public static final String COMPANY = "company";
	public static final String EMAIL = "email";
	public static final String HIREABLE = "hireable";
	public static final String COLLABORATORS = "collaborators";
	public static final String HTML_URL = "html_url";
	public static final String BIO = "bio";
	public static final String TOTAL_PRIVATE_REPOS = "total_private_repos";
	
	public static final String NAME = "name";
	public static final String DISK_USAGE = "disk_usage";
	public static final String PLAN = "plan";
	public static final String OWNED_PRIVATE_REPOS = "owned_private_repos";
	public static final String LOCATION = "location";

	public SinaWeiboProfileDefinition() {
		String[] names = new String[] { URL, COMPANY, NAME, BLOG, LOGIN, EMAIL, LOCATION, TYPE, GRAVATAR_ID, AVATAR_URL,
				HTML_URL, BIO };
		for (String name : names) {
			primary(name, Converters.STRING);
		}
		names = new String[] { FOLLOWING, PUBLIC_REPOS, PUBLIC_GISTS, DISK_USAGE, COLLABORATORS, OWNED_PRIVATE_REPOS,
				TOTAL_PRIVATE_REPOS, PRIVATE_GISTS, FOLLOWERS };
		for (String name : names) {
			primary(name, Converters.INTEGER);
		}
		primary(HIREABLE, Converters.BOOLEAN);
		primary(CREATED_AT, Converters.DATE_TZ_RFC822);
		//primary(PLAN, Converters.JSONstring);
	}

	@Override
	public String getProfileUrl(final OAuth2AccessToken accessToken, final OAuth20Configuration configuration) {
		return String.format(PROFILE_URL, accessToken.getAccessToken());
	}

	@Override
	public SinaWeboProfile extractUserProfile(final String body) throws HttpAction {
		final SinaWeboProfile profile = new SinaWeboProfile();
        JsonNode json = JsonHelper.getFirstNode(body);
        if (json != null) {
            profile.setId(JsonHelper.getElement(json, "id"));
            for (final String attribute : getPrimaryAttributes()) {
				convertAndAdd(profile, attribute, JsonHelper.getElement(json, attribute));
			}
        }
        return profile;
	}

}
