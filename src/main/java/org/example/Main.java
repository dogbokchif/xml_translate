package org.example;

import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        Scanner scanner = new Scanner(System.in);
        PapagoApi api = new PapagoApi();
        System.out.println(api.translateText(Language.CHINESE_CN, Language.ENGLISH, "金鸢尾兰鼠族的文化。"));
        System.out.print("타겟 주소 입력 : ");
        String path = scanner.nextLine();
        File xml = new File(path);

        InputStreamReader input = new InputStreamReader(new FileInputStream(xml), "UTF8");
        Reader reader = new Reader(input, path.substring(0, path.lastIndexOf('\\')));
        reader.rewriteXml(Language.CHINESE_CN, Language.KOREAN);
    }
}