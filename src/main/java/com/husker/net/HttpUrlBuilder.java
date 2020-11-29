package com.husker.net;

public class HttpUrlBuilder extends UrlBuilder{

    public static HttpUrlBuilder create(String url){
        return new HttpUrlBuilder(url);
    }

    public HttpUrlBuilder(String domain) {
        super("http", domain);
    }
}
