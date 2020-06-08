package com.alvoradatecno.sbmongodb.resources.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class URL {
    // No subpacote resources.util, criar classe utilitária URL com um método para decodificar parâmetro de URL

    public static String decodeParam(String text) {
        try {
            return URLDecoder.decode(text, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

}
