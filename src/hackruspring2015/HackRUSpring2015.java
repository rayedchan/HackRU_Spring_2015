package hackruspring2015;

import java.util.List;

/**
 * @author rayedchan
 */
public class HackRUSpring2015
{
    public static void main(String[] args) throws Exception
    {
        GoogleTranslate gTransObj = new GoogleTranslate("AIzaSyApWmJnBSgZc6WSeReIDFQ1m9CRX6c9WwE");
        List<Language> languages =  gTransObj.getSupportedLanguages();
        gTransObj.translate("en", "es", "I do like.");
        gTransObj.translate("en", "de", "Hello, World");
        gTransObj.translate("en", "fr", "Just do it.");
        gTransObj.translate("de", "en", "Hackathon ist super, wir programmieren die ganze Nacht ohne Essen");
    }
}
