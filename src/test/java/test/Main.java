package test;

import com.husker.net.*;


public class Main {

    public static void main(String[] args){
        try {
            Get get = new Get(new HttpsUrlBuilder("github.com"));
            System.out.println(get.getHeader("Content-Type"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
