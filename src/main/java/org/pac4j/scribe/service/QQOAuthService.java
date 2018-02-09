package org.pac4j.scribe.service;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import org.pac4j.scribe.builder.api.QQApi20;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.oauth.OAuth20Service;

public class QQOAuthService {
    // 获取用户 openid 的 URL
    private static final String OPEN_ID_URL = "https://graph.qq.com/oauth2.0/me";
    // 获取用户信息的 URL，oauth_consumer_key 为 apiKey
    private static final String USER_INFO_URL = "https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s";
    // 下面的属性可以通过配置读取
    private String callbackUrl = "http://open.qtdebug.com:8080/oauth/qq/callback"; // QQ 在登陆成功后回调的 URL，这个 URL 必须在 QQ 互联里填写过
    private String apiKey      = "101292272";                                      // QQ 互联应用管理中心的 APP ID
    private String apiSecret   = "5bdbe9403fcc3abe8eba172337904b5a";               // QQ 互联应用管理中心的 APP Key
    private String scope       = "get_user_info";                                  // QQ 互联的 API 接口，访问用户资料
    private OAuth20Service oauthService; // 访问 QQ 服务的 service
    public QQOAuthService() {
        // 创建访问 QQ 服务的 service
        oauthService = new ServiceBuilder().apiKey(apiKey).apiSecret(apiSecret)
                .scope(scope).callback(callbackUrl).build(QQApi20.instance());
    }
    /**
     * 取得 QQ 登陆页面的 URL，例如
     * https://graph.qq.com/oauth2.0/authorize?response_type=code&client_id=101292272&
     * redirect_uri=http://open.qtdebug.com:8080/oauth/qq/callback&scope=get_user_info
     *
     * @return QQ 登陆页面的 URL
     */
    public String getLoginUrl() {
        return oauthService.getAuthorizationUrl();
    }
    /**
     * 使用 code 换取 access token
     *
     * @param code 成功登陆后 QQ Server 返回给回调 URL 的中间 code，用于换取 access token
     * @return 用于访问 QQ 服务的 token
     * @throws IOException
     * @throws ExecutionException 
     * @throws InterruptedException 
     */
    public String getAccessToken(String code) throws IOException, InterruptedException, ExecutionException {
        OAuth2AccessToken token = oauthService.getAccessToken(code); // 使用 code 换取 accessToken
        String accessToken = token.getAccessToken(); // 5943BF2461ED97237B878BECE78A8744
        return accessToken;
    }
    /**
     * 获取用户的 open id，每个用户对于同一个 APP ID 的 open id 是一样的
     *
     * @param accessToken 登陆时从 QQ 系统得到的 access token，作为访问的凭证，相当于用户名密码的作用
     * @return 用户的 open id
     * @throws IOException
     */
    public String getOpenId(String accessToken) throws IOException {
        Response oauthResponse = request(oauthService, accessToken, OPEN_ID_URL);
        // 消息格式: callback( {"client_id":"101292272","openid":"4584E3AAABFC5F052971C278790E9FCF"} );
        String responseBody = oauthResponse.getBody();
        int s = responseBody.indexOf("{");
        int e = responseBody.lastIndexOf("}") + 1;
        String json = responseBody.substring(s, e);
        JSONObject obj = JSONObject.parseObject(json);
        return obj.getString("openid");
    }
    /**
     * 获取用户的信息，QQ 的昵称，QQ 空间的头像等，一般这 2 个属性用的最多
     *
     * @param accessToken 登陆时从 QQ 系统得到的 access token，作为访问的凭证，相当于用户名密码的作用
     * @param openId 用户的 open id
     * @return JSONObject 对象
     * @throws IOException
     */
    public JSONObject getUserInfo(String accessToken, String openId) throws IOException {
        String url = String.format(USER_INFO_URL, apiKey, openId);
        Response oauthResponse = request(oauthService, accessToken, url);
        String responseJson = oauthResponse.getBody();
        return JSON.parseObject(responseJson);
    }
    /**
     * 使用 OAuth 2.0 的方式从服务器获取 URL 指定的信息
     *
     * @param accessToken 登陆时从 QQ 系统得到的 access token，作为访问的凭证，相当于用户名密码的作用
     * @param url 访问 OAuth Server 服务的 URL
     * @return
     */
    public Response request(OAuth20Service service, String accessToken, String url) {
        OAuth2AccessToken token = new OAuth2AccessToken(accessToken);
        OAuthRequest oauthRequest = new OAuthRequest(service.getApi().getAccessTokenVerb(), url, null);
        service.signRequest(token, oauthRequest); // 会把 accessToken 添加到请求中，GET 请求即添加到 URL 上
       /* Response oauthResponse = oauthRequest.send();
        return oauthResponse;*/
        return null;
    }
}
