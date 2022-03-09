/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shared.API;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import static javax.management.Query.attr;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author l3ej
 */
public class SendInBlueAPI implements JSONDataSource {
    private static SendInBlueAPI sendInBlueAPI;
    private JSONArray items;
    private static final String URL = "https://api.sendinblue.com/v3/emailCampaigns";
    private URL url ;
    private String fURL;
    private String receptionMail;
    private HttpURLConnection httpURLConnection ;
    private BufferedReader in;
    private StringBuffer response;
    private String inputLine;
    private String key = "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";

    public SendInBlueAPI(String reciever) {
        this.fURL = String.format(URL, reciever,"%s");
    }

    
    
    @Override
    public List<String> fetchResult(String query) {
        List<String> resultList = new ArrayList();
        if(query.equals("")) return resultList;
        query = query.replaceAll(" ", "%20");
        try {
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
                resultList.add(items.getJSONObject(i).getString(receptionMail));
            }
        } catch (IOException | JSONException ex) {
            System.out.println(ex.getMessage());
        }
        return resultList;
    }
}
