package edu.rutgers.hackruspring2015;

import edu.rutgers.hackruspring2015.gson.lang.objects.Language;
import edu.rutgers.hackruspring2015.gson.lang.objects.MainObject;
import edu.rutgers.hackruspring2015.gson.lang.objects.Data;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
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
    public List<Language> getSupportedLanguages() throws MalformedURLException, IOException, Exception
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
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

                // Convert JSON format data into custom Java Object 
                Gson gson = new Gson();
                MainObject obj = gson.fromJson(reader, MainObject.class);
                languages = ((Data) obj.getData()).getLanguages();
            }
            
            // Failed
            else
            {
                inputStream = conn.getErrorStream(); // Get errors
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                
                // [Logging] Construct data given from call to String object
                StringBuilder result = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) 
                {
                    result.append(line);

                }
                logger.log(Level.INFO, "Errors: {0}", new Object[]{result});
                throw new Exception("Failed to get supported languages.");
            }
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
    
    /**
     * Translate a text from source language to target language
     * Test Command:
     * curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET ‘https://www.googleapis.com/language/translate/v2?key=INSERT_YOUR_KEY&source=en&target=de&q=Hello%20world’
     * Refer for language codes:
     * https://cloud.google.com/translate/v2/using_rest#supported-query-params
     * @param srcLang       Source Language Code
     * @param targetLang    Target Language Code
     * @param srcText       Source Language Text
     * @return Target Translated Text
     * @throws UnsupportedEncodingException
     * @throws MalformedURLException
     * @throws IOException
     * @throws Exception 
     */
    public String translate(String srcLang, String targetLang, String srcText) throws UnsupportedEncodingException, MalformedURLException, IOException, Exception
    {
        HttpsURLConnection conn = null;
        InputStream inputStream = null;
        
        try
        {
            // Encode the source text to UTF-8
            String encodedText = URLEncoder.encode(srcText, "UTF-8");
            
            // Construct URL for Google API service call
            String urlStr = "https://www.googleapis.com/language/translate/v2?key=" + this.apiKey + "&q=" + encodedText + "&target=" + targetLang + "&source=" + srcLang;
            URL url = new URL(urlStr);
            conn = (HttpsURLConnection) url.openConnection();
            
            // Get response code for Google Service call
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
            
            // Read data input stream
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
           
            // Construct String of data from input stream
            StringBuilder strBuilder = new StringBuilder();
            String line; 
            while ((line = reader.readLine()) != null)
            {
                strBuilder.append(line);
            }
            logger.log(Level.INFO, "JSON Format: {0}", new Object[]{strBuilder.toString()});

            // Throw exception
            if(responseCode != 200)
            {
                throw new Exception(strBuilder.toString());
            }
          
            // Setup JSON Parser
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(strBuilder.toString());
 
            // Check if element is JSON object
            if (element.isJsonObject()) 
            {
                JsonObject obj = element.getAsJsonObject();
                if (obj.get("error") == null) 
                {
                    // Get the translated value from JSON data
                    String translatedText = obj.get("data").getAsJsonObject().get("translations").getAsJsonArray().get(0).getAsJsonObject().get("translatedText").getAsString();        
                    logger.log(Level.INFO, "Translated Results: {0}", new Object[]{translatedText});
                    return translatedText;
                }
            }
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
        
        return null;
    }
}
