package com.github.scribejava.apis.examples;

import org.pac4j.scribe.builder.api.BaiduApi20;

import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.oauth.OAuth20Service;

public class BaiduExample {

	public static void main(String[] args) {

		final OAuth20Service oschina = new ServiceBuilder("EaIglDEnH58bipirI2kIGYpP")
				.apiSecret("VQ9n3GGcBhNx58DKK8DG9iGphb6u2e88").callback("https://vindell.github.io")
				.responseType("code").build(BaiduApi20.instance());
		System.out.println(oschina.getAuthorizationUrl());
	}
	
}
