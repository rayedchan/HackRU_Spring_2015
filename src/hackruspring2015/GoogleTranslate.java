package hackruspring2015;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.HttpsURLConnection;

/**
 * Uses Google Translate API
 * - Restful calling style
 * - JSON data 
 * @author rayedchan
 */
public class GoogleTranslate 
{
    private final String apiKey;
    private final static Logger logger = Logger.getLogger(GoogleTranslate.class.getName());
    
    /**
     * Constructor
     * @param apiKey Google API key for server application
     */
    public GoogleTranslate(String apiKey)
    {
        this.apiKey = apiKey;
    }
    
    /**
     * Gets all the supported languages for Google Translate API.
     * Test Command: curl -X GET https://www.googleapis.com/language/translate/v2/languages?key=INSERT-YOUR-KEY
     * @return a list of language codes supported
     * @throws MalformedURLException
     * @throws IOException 
     */
    public List<Language> getSupportedLanguages() throws MalformedURLException, IOException
    {
        HttpsURLConnection conn = null;
        InputStream inputStream = null;
        List<Language> languages = null;
        
        try
        {
            String urlStr = "https://www.googleapis.com/language/translate/v2/languages?key=" + this.apiKey;
            URL url = new URL(urlStr);
            conn = (HttpsURLConnection) url.openConnection();
            
            // Get response code for HTTP call
            int responseCode = conn.getResponseCode();
            logger.log(Level.INFO, "Response Code: {0}", new Object[]{responseCode});
            
            // Success response
            if(responseCode == 200)
            {
                inputStream = conn.getInputStream(); // Get input
            }
            
            // Failed
            else
            {
                inputStream = conn.getErrorStream(); // Get errors
            }
            
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            
            // [Logging] Construct data given from call to String object
            /*
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) 
            {
                result.append(line);
            
            }
            logger.log(Level.INFO, "Results: {0}", new Object[]{result});
            */
           
            // Convert JSON format data into custom Java Object 
            Gson gson = new Gson();
            MainObject obj = gson.fromJson(reader, MainObject.class);
            languages = ((Data) obj.getData()).getLanguages();
        }
        
        finally
        {
            if(inputStream != null)
            {
                inputStream.close();
            }
            
            if(conn != null)
            {
                conn.disconnect();
            }
        }
        
        logger.log(Level.INFO, "Results: {0}", new Object[]{languages});
        return languages;
    }
    
    public String translate(String srcLang, String targetLang, String srcText)
    {
        String result = "";
        return result;
    }
}
