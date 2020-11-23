package com.husker.net;

public class HttpsUrlBuilder extends UrlBuilder{
    public HttpsUrlBuilder(String domain) {
        super("https", domain);
    }
}
