package com.github.scribejava.apis.examples;

import org.pac4j.scribe.builder.api.YibanApi20;

import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.oauth.OAuth20Service;

public class YibanExample {

	public static void main(String[] args) {

		final String apiKey = "101303927";
		final String apiSecret = "0c3ac6430d6e2f60dfb637101252417e ";
		final OAuth20Service service = new ServiceBuilder(apiKey).apiSecret(apiSecret)
				//.withScope("get_user_info,list_album,upload_pic,do_like")
				.callback("http://www.yichisancun.com/qqlogin.htm").build(YibanApi20.instance());

		System.out.println(service.getAuthorizationUrl());
	}
}
