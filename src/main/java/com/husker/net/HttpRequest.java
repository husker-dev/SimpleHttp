package com.husker.net;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class HttpRequest {

    private final String url;
    private HttpURLConnection connection;
    private ArrayList<HttpCookie> cookies = new ArrayList<>();
    private HashMap<String, String> parameters = new HashMap<>();

    private boolean executed = false;
    private String htmlContent = null;

    public HttpRequest(CharSequence url){
        this.url = url.toString();
    }

    public void addCookie(HttpCookie... cookies){
        this.cookies.addAll(Arrays.asList(cookies));
    }

    public void setCookies(HttpCookie[] cookies){
        this.cookies = new ArrayList<>(Arrays.asList(cookies));
    }

    public HttpCookie[] getCookies(){
        return cookies.toArray(new HttpCookie[0]);
    }

    public String getHeader(String name) throws IOException {
        if(!executed)
            execute();
        return connection.getHeaderField(name);
    }

    public String getHtmlContent() throws IOException{
        if(!executed)
            execute();
        if(htmlContent == null) {
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
            StringBuilder out = new StringBuilder();

            String inputLine;
            while ((inputLine = br.readLine()) != null)
                out.append(inputLine).append("\n");
            br.close();
            htmlContent = out.toString();
        }
        return htmlContent;
    }

    public InputStream getInputStream() throws IOException {
        return connection.getInputStream();
    }

    public HttpURLConnection getConnection(){
        return connection;
    }

    public HttpRequest set(String key, String value) {
        parameters.put(key, value);
        return this;
    }

    public void setParameters(HashMap<String, String> parameters){
        this.parameters = parameters;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

    public String getUrl(){
        return url;
    }

    protected HttpURLConnection createClient(String url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection)new URL(url).openConnection();
        connection.setDoOutput(true);
        return connection;
    }

    public void execute() throws IOException {
        executed = true;
        htmlContent = null;

        // Set cookie manager for current request
        CookieManager cookieManager = new CookieManager();
        CookieHandler.setDefault(cookieManager);

        // Create connection
        connection = createClient(getUrl());

        // Set cookies
        StringBuilder cookiesString = new StringBuilder();
        for(HttpCookie cookie : cookies)
            cookiesString.append(cookie.toString()).append(";");
        connection.setRequestProperty("Cookie", cookiesString.toString());

        // Connect
        onConnect();

        // Rewrite cookies
        HashMap<String, HttpCookie> cookieHashMap = new HashMap<>();
        for(HttpCookie cookie : cookies)
            cookieHashMap.put(cookie.getName(), cookie);

        for(HttpCookie loadedCookie : cookieManager.getCookieStore().getCookies()){
            if(cookieHashMap.containsKey(loadedCookie.getName()))
                cookies.remove(cookieHashMap.get(loadedCookie.getName()));
            cookies.add(loadedCookie);
        }
    }

    protected void onConnect() throws IOException {
        connection.getContent();
    }

    protected static String getClearUrl(String url){
        if(url.contains("?"))
            return url.split("\\?")[0];
        else
            return url;
    }

}
