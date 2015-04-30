package edu.rutgers.hackruspring2015;

import edu.rutgers.hackruspring2015.gson.lang.objects.Language;
import java.util.List;

/**
 * @author rayedchan
 */
public class HackRUSpring2015
{
    public static String GOOGLE_API_KEY = "INSERT_GOOGLE_API_KEY";
    
    public static void main(String[] args) throws Exception
    {
        GOOGLE_API_KEY = System.getenv("GOOGLE_API_KEY"); // Fetch from environment variable
        System.out.println(GOOGLE_API_KEY);
        
        GoogleTranslate gTransObj = new GoogleTranslate(GOOGLE_API_KEY);
        List<Language> languages =  gTransObj.getSupportedLanguages();
        gTransObj.translate("en", "es", "I do like.");
        gTransObj.translate("en", "de", "Hello, World");
        gTransObj.translate("en", "fr", "Just do it.");
        gTransObj.translate("de", "en", "Hackathon ist super, wir programmieren die ganze Nacht ohne Essen");
    }
}
