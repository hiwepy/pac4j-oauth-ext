/*
 * 版权所有.(c)2008-2018. 极蚁网络工作室 (http://jeebiz.net).
 */
package com.github.scribejava.apis.examples;

import org.pac4j.core.util.CommonHelper;
import org.pac4j.scribe.builder.api.WeiXinApi20;

import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.oauth.OAuth20Service;

public class WeiXinExample {

	public static void main(String[] args) {

		final String apiKey = "wxbdc5610cc59c1631";
		final String apiSecret = "x ";
		final OAuth20Service service = new ServiceBuilder().apiKey(apiKey).apiSecret(apiSecret)
				.callback("https://passport.yhd.com/wechat/login.do").state("xxxx").scope("snsapi_login")
				.responseType("code").build(WeiXinApi20.instance());
		System.out.println(service.getAuthorizationUrl());

		final String s = "http://wx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/0"
				.replaceAll("\\/", "/");
		System.out.println(CommonHelper.asURI(s));
	}

}
