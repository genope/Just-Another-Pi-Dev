/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shared.API;

import com.google.api.client.json.JsonParser;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import shared.connexion.MaConnexion;
import shared.entities.Panier;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author l3ej
 */
public class YoutubeAPI {
//    URLConnection connection = new URL(“<some_url>/<endpoint>?param1=value1&param2=value2”).openConnection();
//connection.setRequestProperty("header1", header1);
//connection.setRequestProperty("header2", header2);
////Get Response  
//InputStream is = connection.getInputStream();
//System.out.println(connection.getContentType());

    String URL = "https://youtube.googleapis.com/youtube/v3/videos?part=snippet&chart=mostPopular&regionCode=US&key=AIzaSyDXuwhjjbubI1UO0tr6pH2oTal88btv9YI";
    String charset = "UTF-8";
    String youtube_host = "youtube.googleapis.com/";
    String youtube_key = "AIzaSyDXuwhjjbubI1UO0tr6pH2oTal88btv9YI";
    String s = "Pulp";
    //String queryy = String.format("s=%s", URLEncoder.encode(s, charset));
    JSONArray items;
    String fURL;
    String attr = "title";
    URL url;
    HttpURLConnection httpURLConnection;
    BufferedReader in;
    StringBuffer response;
    String inputLine;

    public YoutubeAPI(String type) {
        this.fURL = String.format(URL, type, "%s");
    }

    public List<String> fetchResult(String query) throws IOException {
        List<String> resultList = new ArrayList();
        try {
            if (query.equals("")) {
                return resultList;
            }
            query = query.replaceAll(" ", "%20");

            url = new URL(String.format(fURL, query));
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            items = new JSONObject(response.toString()).getJSONArray("results");
            for(int i=0;i<items.length();i++)
            {
                resultList.add(items.getJSONObject(i).getString(attr));
            }
        } catch (MalformedURLException ex) {
            System.out.println(ex.getMessage());
        }
        return resultList;
    }

}
