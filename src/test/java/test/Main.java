package test;

import com.husker.net.Get;
import com.husker.net.Http;

import java.io.IOException;
import java.util.Arrays;

public class Main {

    public static void main(String[] args){
        try {
            Http http = new Http();
            http.get("https://vk.com");
            System.out.println(Arrays.toString(http.getCookies()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
