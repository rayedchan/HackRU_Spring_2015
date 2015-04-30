package edu.rutgers.hackruspring2015.gson.lang.objects;

import java.util.List;

/**
 * Nested layer of MainObject class to represent JSON data of the supported languages
 * of Google Translate API.
 * 
 * Example:
 * The nested curly brackets (value of "data") represents the Data object.
 * {
 *    "data": 
 *    {
 *       "languages": [ {"language": "af"}, {"language": "ar"} ]
 *    }
 * }
 * 
 * @author rayedchan 
 */
public class Data
{
    private List<Language> languages;
    
    public List<Language> getLanguages()
    {
        return this.languages;
    }
    public void setLanguages(List<Language> languages)
    {
        this.languages = languages;
    }
    
    @Override
    public String toString()
    {
        return String.format("Languages: %s", this.languages);
    }
}
