package com.life.isthepageup.controller;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UrlCheckController {

    private String SITE_IS_UP="Working";
    private String SITE_IS_DOWN="Not Working";
    private String INCORRECT_URL="Incorrect";

    @GetMapping("/check")
    public String getPUrlStatusMessage(@RequestParam String url)
    {
        String returnMessage="";
        try {
            URL urlObj = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
            conn.setRequestMethod("GET");

            int responseCategory = conn.getResponseCode() / 100;
            if(responseCategory != 2 )
                returnMessage = SITE_IS_DOWN;
            else 
                returnMessage = SITE_IS_UP;
          
        } catch (MalformedURLException e) {
            returnMessage = INCORRECT_URL;
        } catch (IOException e) {
            returnMessage = SITE_IS_DOWN;
        }
        


        return returnMessage;
        
    }


    
}
