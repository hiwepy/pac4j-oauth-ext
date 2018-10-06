package org.pac4j.oauth.profile.weixin;

import org.pac4j.core.client.IndirectClient;
import org.pac4j.oauth.config.OAuth20Configuration;
import org.pac4j.oauth.profile.creator.OAuth20ProfileCreator;

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;

/**
 */
public class WeiXinProfileCreator extends OAuth20ProfileCreator<WeiXinProfile> {

	@SuppressWarnings("rawtypes")
	public WeiXinProfileCreator(OAuth20Configuration configuration, IndirectClient client) {
		super(configuration, client);
	}

	
	@Override
	protected String sendRequestForData(OAuth20Service service, OAuth2AccessToken accessToken, String dataUrl,
			Verb verb) {
		// 当且仅当该网站应用已获得该用户的userinfo授权时，才会出现该字段。
		//String unionid = accessToken.getParameter(OAuth2Constants.UNIONID);
		// 授权用户唯一标识
		//String openid = accessToken.getParameter(OAuth2Constants.OPENID);
		return super.sendRequestForData(service, accessToken, dataUrl, verb);
	}

}
