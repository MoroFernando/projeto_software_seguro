package main.util;

import java.text.Normalizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValidator {

    public InputValidator(){

    }

    public String validateString(String s){
        // Normaliza
        s = Normalizer.normalize(s, Normalizer.Form.NFKC);

        // Valida
        Pattern pattern = Pattern.compile("[<>]");
        Matcher matcher = pattern.matcher(s);
        if (matcher.find()) {
            s = matcher.replaceAll("");
            System.out.println("\u001B[31mRemovido caracteres ilegais\u001B[0m: " + s);
        }

        return s;
    }
}
