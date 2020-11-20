package com.husker.net;


import java.io.IOException;
import java.net.HttpCookie;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Http {
    public static boolean debug = false;

    private HttpRequest request;
    private ArrayList<HttpCookie> cookies = new ArrayList<>();

    public Http get(CharSequence url) throws IOException {
        return get(url, null);
    }

    public Http get(CharSequence url, HashMap<String, String> parameters) throws IOException {
        request = new Get(url);
        request.setCookies(cookies.toArray(new HttpCookie[0]));
        if(parameters != null)
            request.setParameters(parameters);
        request.execute();
        cookies = new ArrayList<>(Arrays.asList(request.getCookies()));

        if(debug)
            printInfo();
        return this;
    }

    public Http post(CharSequence url) throws IOException {
        return post(url, null);
    }

    public Http post(CharSequence url, HashMap<String, String> parameters) throws IOException {
        request = new Post(url);
        request.setCookies(cookies.toArray(new HttpCookie[0]));
        if(parameters != null)
            request.setParameters(parameters);
        request.execute();
        cookies = new ArrayList<>(Arrays.asList(request.getCookies()));

        if(debug)
            printInfo();
        return this;
    }

    public String getHeader(String name){
        return request == null ? null : request.getHeader(name);
    }

    public String getUrl(){
        return request.getUrl();
    }

    public HttpCookie[] getCookies(){
        return cookies.toArray(new HttpCookie[0]);
    }

    public void addCookie(HttpCookie... cookies){
        this.cookies.addAll(Arrays.asList(cookies));
    }

    public String getHtmlContent() {
        try {
            return request.getHtmlContent();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void clearCookies(){
        cookies.clear();
    }

    public void toLocation() throws IOException {
        get(getHeader("location"));
    }

    public void printInfo(){
        System.out.println("-------------------------------");
        System.out.println((request instanceof Get ? "GET " : "POST ") + request.getUrl());
        System.out.println("Cookies: ");
        for(HttpCookie cookie : getCookies())
            System.out.println(" " + cookie.toString());
        System.out.println("Location: " + request.getHeader("location"));
        System.out.println("Content: " + getHtmlContent());
    }


}
