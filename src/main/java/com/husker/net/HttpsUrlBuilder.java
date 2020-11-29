package com.husker.net;

public class HttpsUrlBuilder extends UrlBuilder{

    public static HttpsUrlBuilder create(String url){
        return new HttpsUrlBuilder(url);
    }

    public HttpsUrlBuilder(String domain) {
        super("https", domain);
    }
}
