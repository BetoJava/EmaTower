package scripts.display.utils;

import java.io.UnsupportedEncodingException;


public final class Text {

    /**
     * Retourne un string sous forme UTF8.
     * @param text
     * @return
     */
    public static String uniString(String text) {
        try {
            String str = new String(text.getBytes(), "UTF8");
            return str;
        } catch (UnsupportedEncodingException e) {
            return text;
        }

    }
}
