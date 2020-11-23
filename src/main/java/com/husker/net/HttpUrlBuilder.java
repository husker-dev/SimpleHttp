package com.husker.net;

public class HttpUrlBuilder extends UrlBuilder{

    public HttpUrlBuilder(String domain) {
        super("http", domain);
    }
}
