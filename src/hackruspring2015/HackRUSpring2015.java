package hackruspring2015;

import java.util.ArrayList;

/**
 * @author rayedchan
 */
public class HackRUSpring2015
{
    public static void main(String[] args) throws Exception
    {
        GoogleTranslate gTransObj = new GoogleTranslate("AIzaSyApWmJnBSgZc6WSeReIDFQ1m9CRX6c9WwE");
        gTransObj.getSupportedLanguages();
    }
    
}
