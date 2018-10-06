package org.pac4j.oauth.profile.sina;

import org.pac4j.core.client.IndirectClient;
import org.pac4j.oauth.config.OAuth20Configuration;
import org.pac4j.oauth.profile.creator.OAuth20ProfileCreator;

import com.alibaba.fastjson.JSONObject;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;

/**
 * http://open.weibo.com/wiki/2/account/get_uid
 */
public class SinaWeiboProfileCreator extends OAuth20ProfileCreator<SinaWeiboProfile> {

	

	private static final String UID_URL = "https://api.weibo.com/2/account/get_uid.json";
	
	private String uid;

	public SinaWeiboProfileCreator(OAuth20Configuration configuration, IndirectClient client) {
		super(configuration, client);
	}
    
     
    
    @Override
    protected void signRequest(OAuth20Service service, OAuth2AccessToken accessToken, OAuthRequest request) {
    	super.signRequest(service, accessToken, request);
    	/*try {
			
			//  使用Access Token来获取用户的UID
	        String uidUrl = String.format(UID_URL, accessToken);
	        
	        OAuthRequest openIdRequest = createOAuthRequest(uidUrl, Verb.GET);
	        Response response = openIdRequest.send();
	        int code = response.getCode();
	        
			String body = response.getBody();
			
			 返回结果
			 {
			     "uid":"3456676543"
			 }
			 错误返回值格式
			 {
			 	"request" : "/statuses/home_timeline.json",
			 	"error_code" : "20502",
			 	"error" : "Need you follow uid."
			 }
			 
			JsonNode json = JsonHelper.getFirstNode(body);
			if (code != 200 || JsonHelper.getElement(json, "error_code") != null) {
	        	logger.error("Failed to get uid, code : " + code + " / body : " + body);
	            throw new HttpCommunicationException(code, body);
	        }
			uid = String.valueOf(JsonHelper.getElement(json, "uid"));
			request.addQuerystringParameter(OAuth2Constants.UID, uid);
	        
		} catch (IOException e) {
			throw new TechnicalException(e);
		}*/
    }
    
    @Override
    protected String sendRequestForData(OAuth20Service service, OAuth2AccessToken accessToken, String dataUrl,
    		Verb verb) {
    	return JSONObject.parseObject(super.sendRequestForData(service, accessToken, dataUrl, verb)).put("uid", uid).toString();
    }

}
