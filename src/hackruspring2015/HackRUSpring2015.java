package hackruspring2015;

import java.util.List;

/**
 * @author rayedchan
 */
public class HackRUSpring2015
{
    public static final String GOOGLE_API_KEY = "INSERT API KEY HERE";
    
    public static void main(String[] args) throws Exception
    {
        GoogleTranslate gTransObj = new GoogleTranslate(GOOGLE_API_KEY);
        List<Language> languages =  gTransObj.getSupportedLanguages();
        gTransObj.translate("en", "es", "I do like.");
        gTransObj.translate("en", "de", "Hello, World");
        gTransObj.translate("en", "fr", "Just do it.");
        gTransObj.translate("de", "en", "Hackathon ist super, wir programmieren die ganze Nacht ohne Essen");
    }
}
