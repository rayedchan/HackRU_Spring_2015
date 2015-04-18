package hackruspring2015;

/**
 * Represents a single language
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
