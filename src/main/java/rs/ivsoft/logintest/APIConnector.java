package rs.ivsoft.logintest;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Milan on 20.3.2016..
 */
public class APIConnector {
    public JSONArray GetAll() {
        JSONArray jsonArray = null;
        try {
            URL url = new URL("http://192.168.1.2/api.php");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.connect();

            InputStream is = conn.getInputStream();
            BufferedReader streamReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            StringBuilder responseStrBuilder = new StringBuilder();

            String inputStr;
            while ((inputStr = streamReader.readLine()) != null)
                responseStrBuilder.append(inputStr);

            jsonArray = new JSONArray(responseStrBuilder.toString());

        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
        return jsonArray;
    }
}
