package com.husker.net;

import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;

public class UrlBuilder implements CharSequence {

    private final LinkedHashMap<String, String> parameters = new LinkedHashMap<>();
    private final String domain;
    private final String protocol;

    public UrlBuilder(String protocol, String domain){
        this.protocol = protocol;
        if(domain.contains("://"))
            domain = domain.split("://")[1];
        if(domain.contains("?")) {
            for (String parameter : domain.split("\\?")[1].split("&")) {
                String[] par = parameter.split("=");
                set(par[0], par.length == 2 ? par[1] : null);
            }
            domain = domain.split("\\?")[0];
        }
        this.domain = domain;
    }

    public UrlBuilder set(String key, Object value){
        parameters.put(key, value.toString());
        return this;
    }

    public UrlBuilder remove(String key){
        parameters.remove(key);
        return this;
    }

    public String getProtocol(String protocol){
        return protocol;
    }

    public String toString(){
        try {
            StringBuilder builder = new StringBuilder();
            builder.append(protocol).append("://").append(domain).append((!domain.contains("&") && parameters.size() > 0) ? "?" : "");

            for (Map.Entry<String, String> entry : parameters.entrySet()) {
                if (builder.toString().charAt(builder.toString().length() - 1) != '?')
                    builder.append("&");
                builder.append(URLEncoder.encode(entry.getKey(), "UTF-8")).append("=").append(URLEncoder.encode(entry.getValue(), "UTF-8"));
            }

            return builder.toString();
        }catch (Exception ex){
            ex.printStackTrace();
            return "";
        }
    }

    public int length() {
        return toString().length();
    }

    public char charAt(int index) {
        return toString().charAt(index);
    }

    public CharSequence subSequence(int start, int end) {
        return toString().subSequence(start, end);
    }
}
