package edu.rutgers.hackruspring2015.gson.lang.objects;

/**
 * Represents a single language.
 * Used to populate this object with JSON data of the supported 
 * languages from Google Translate API call.
 * 
 * Example:
 * The "languages" key in JSON maps to a list of language objects. 
 * {
 *    "data": 
 *    {
 *       "languages": [ {"language": "af"}, {"language": "ar"} ]
 *    }
 * }
 * 
 * @author rayedchan 
 */
public class Language 
{
    private String language;
    
    public String getLanguage()
    {
        return this.language;
    }
    public void setLanguage(String language)
    {
        this.language = language;
    }
    
    @Override
    public String toString()
    {
        return String.format("%s", this.language);
    }
}
