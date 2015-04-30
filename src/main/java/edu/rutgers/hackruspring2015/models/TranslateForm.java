package edu.rutgers.hackruspring2015.models;

/**
 * Translate Form
 * @author rayedchan
 */
public class TranslateForm 
{
    private String text;
    private String targetLanguage;
    private String sourceLanguage;
    
    public String getText()
    {
        return this.text;
    }
    
    public void setText(String text)
    {
        this.text = text;
    }
    
    public String getTargetLanguage()
    {
        return this.targetLanguage;
    }
    
    public void setTargetLanguage(String targetLanguage)
    {
        this.targetLanguage = targetLanguage;
    }
    
    public String getSourceLanguage()
    {
        return this.sourceLanguage;
    }
    
    public void setSourceLanguage(String sourceLanguage)
    {
        this.sourceLanguage = sourceLanguage;
    }
}
