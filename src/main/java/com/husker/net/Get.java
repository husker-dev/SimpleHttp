package com.husker.net;


import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Map;

public class Get extends HttpRequest{

    public Get(CharSequence url) {
        super(getClearUrl(url.toString()));

        String urlString = url.toString();
        if(urlString.contains("?")) {
            for (String parameter : urlString.split("\\?")[1].split("&")) {
                String[] par = parameter.split("=");
                set(par[0], par.length == 2 ? par[1] : null);
            }
        }
    }

    public String getUrl() {
        String url = super.getUrl();
        if(getParameters().size() > 0){
            StringBuilder parameters = new StringBuilder();
            for(Map.Entry<String, String> entry : getParameters().entrySet())
                parameters.append("&").append(entry.getKey()).append("=").append(entry.getValue() == null ? "" : entry.getValue());

            url += parameters.toString().replaceFirst("&", "?");
        }
        return url;
    }

    protected HttpURLConnection createClient(String url) throws IOException {
        HttpURLConnection connection = super.createClient(url);
        connection.setRequestMethod("GET");

        return connection;
    }
}
