package edu.rutgers.hackruspring2015.gson.lang.objects;

/**
 * Top layer object to represent JSON data of the supported languages
 * of Google Translate API.
 * 
 * Example:
 * The outermost curly brackets represents the MainObject.
 * 
 * {
 *    "data": 
 *    {
 *       "languages": [ {"language": "af"}, {"language": "ar"} ]
 *    }
 * }
 * 
 * @author rayedchan 
 */
public class MainObject 
{
    private Data data;
    
    public Object getData()
    {
        return this.data;
    }
    
    public void setData(Data data)
    {
        this.data = data;
    }
}
