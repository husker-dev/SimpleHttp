package test;

import com.husker.net.Get;
import com.husker.net.Http;
import com.husker.net.UrlBuilder;

import java.io.IOException;
import java.util.Arrays;

public class Main {

    public static void main(String[] args){
        try {
            Http http = new Http();
            Http.debug = true;

            http.get("https://vk.com/");
            String lg_h = http.getHtmlContent().split("lg_h=")[1].split("&")[0];

            http.post(new UrlBuilder("login.vk.com")
                    .set("act", "login")
                    .set("role", "al_frame")
                    .set("_origin", "https://vk.com")
                    .set("utf8", 1)
                    .set("email", "l")
                    .set("pass", "p")
                    .set("lg_h", lg_h)
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
