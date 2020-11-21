package com.husker.net;


import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class Post extends HttpRequest {

    public Post(CharSequence url) {
        super(url);
    }

    protected HttpURLConnection createClient(String url) throws IOException {
        HttpURLConnection connection = super.createClient(url);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.setRequestProperty("Content-Length", String.valueOf(getData().length));

        return connection;
    }

    protected void onConnect() throws IOException {
        getConnection().setDoOutput(true);
        getConnection().getOutputStream().write(getData());
        super.onConnect();
    }

    private byte[] getData(){
        StringBuilder postData = new StringBuilder();
        try {
            for (Map.Entry<String, String> param : getParameters().entrySet()) {
                if (postData.length() != 0) postData.append('&');
                postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
                postData.append('=');
                postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
            }
        }catch (Exception ignored){
        }
        return postData.toString().getBytes(StandardCharsets.UTF_8);
    }
}
