package com.github.scribejava.apis.examples;

import org.pac4j.scribe.builder.api.OschinaApi20;

import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.oauth.OAuth20Service;

public class OsChinaExample {

	public static void main(String[] args) {

		final OAuth20Service oschina = new ServiceBuilder().apiKey("ap4CsYvTlSbHcQYVaoSM")
				.apiSecret("tpgjHGhh4C5gPzFSMR8hcG9bDPMMfODH").callback("https://vindell.github.io")
				.responseType("code").build(OschinaApi20.instance());
		System.out.println(oschina.getAuthorizationUrl());
	}
	
}
