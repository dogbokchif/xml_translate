package org.example;

import org.json.simple.parser.ParseException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Reader {
    final InputStreamReader input;
    FileOutputStream output;

    public Reader(InputStreamReader input, String output) throws IOException {
        this.input = input;
        this.output = new FileOutputStream(output + "\\output.xml");
    }

    public void rewriteXml(Language origin, Language target) throws IOException, ParseException {
        PapagoApi api = new PapagoApi();

        boolean on = true;
        String text = "";
        int c;
        input.read();
        while ((c = input.read()) != -1) {
            char character = (char) c;
            if (character == '<') {
                on = true;
                if (! text.isEmpty()) {
                    boolean trans = false;
                    for (int i = 0; i < text.length(); i++) {
                        if (text.charAt(i) != ' ') {
                            trans = true;
                        }
                    }
                    if (trans) {
                        System.out.println("번역 요청 : " + text);
                        output.write(api.translateText(origin, target, text).getBytes());
                    } else {
                        output.write(text.getBytes());
                    }
                }
                text = "";
                output.write('<');
            } else if (character == '>') {
                on = false;
                text = "";
                output.write('>');
            } else {
                if (! (character == '\n' || character == '\r')) {
                    if (on) {
                        output.write(character);
                    } else {
                        text += character;
                        System.out.print("> ");
                    }
                } else {
                    output.write(text.getBytes());
                    text = "";
                    output.write(character);
                }
            }

            System.out.println("글자 추가 : " + (character != '\r' ? character : "개행") + " " + c);
        }
        input.close();
        output.close();
    }
}
