package test;

import com.husker.net.*;


public class Main {

    public static void main(String[] args){
        try {
            Http http = new Http();
            Http.debug = true;
            http.get(HttpsUrlBuilder.create("github.com?c=a").set("a", "b"));
            //Get get = new Get(new HttpsUrlBuilder("github.com"));
            //System.out.println(get.getHeader("Content-Type"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
