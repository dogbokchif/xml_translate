package org.example;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class PapagoApi {
    final URL apiUrl;
    final String id;
    final String secret;

    public PapagoApi() throws IOException {
        apiUrl = new URL("https://openapi.naver.com/v1/papago/n2mt");

        id = "Doa22Bws16r8XXZ2nBP_";
        secret = "zmiMy1zpSD";
    }

    public String translateText(Language origin, Language target, String text) throws IOException, ParseException {
        HttpURLConnection con = (HttpURLConnection) apiUrl.openConnection();

        con.setRequestProperty("X-Naver-Client-Id", id);
        con.setRequestProperty("X-Naver-Client-Secret", secret);

        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        con.setDoInput(true);
        con.setDoOutput(true);
        con.setUseCaches(false);
        con.setDefaultUseCaches(false);

        OutputStreamWriter osw = new OutputStreamWriter(con.getOutputStream());
        osw.write("source=" + origin + "&target=" + target + "&text=" + text);
        osw.flush();

        int resCode = con.getResponseCode();

        JSONParser parser = new JSONParser();
        JSONObject object = (JSONObject) parser.parse(new InputStreamReader(con.getInputStream()));
        JSONObject result = (JSONObject) ((JSONObject) object.get("message")).get("result");

        return result.get("translatedText").toString();
    }
}
