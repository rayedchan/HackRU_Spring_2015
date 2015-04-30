package edu.rutgers.hackruspring2015.controllers;

import edu.rutgers.hackruspring2015.GoogleTranslate;
import edu.rutgers.hackruspring2015.gson.lang.objects.Language;
import edu.rutgers.hackruspring2015.models.TranslateForm;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author rayedchan
 */
@Controller
public class TranslateController 
{
    // Custom Google Translate Utility
    private final GoogleTranslate gTranslateOps = new GoogleTranslate("AIzaSyBlEDmdkVmMLr0rE0DxkuDY-KEnP873EbE"); 
    // TODO: Get API Key from system variable
    
    @RequestMapping(value="/translate", method = RequestMethod.GET)
    public ModelAndView viewTranslateForm(ModelMap model) throws Exception 
    {
        // Populate combox box for supported languages
        HashMap<String,String> languages = getSupportedLanguages();
        Map<String, String> sortedLanguagesMap = sortByComparator(languages);
	model.addAttribute("languages", sortedLanguagesMap);
        
        // Create new Object to represent form
        TranslateForm translateForm = new TranslateForm(); // to be used to store input from form
        String viewName = "translate"; // jsp file name 
        String commandName = "userTranslateForm"; // value of attribute "commandName" on HTML form
        return new ModelAndView(viewName, commandName, translateForm);
    }
     
    @RequestMapping(value="/translate/process", method = RequestMethod.POST)
    public String processTranslationForm(@ModelAttribute("userTranslateForm") TranslateForm formObj, ModelMap model)
    {
        String viewName = "result";
   
        try
        {
             // Get form input 
            String srcText = formObj.getText();
            String targetLanguage = formObj.getTargetLanguage();
            
            // Google API to translate
            String translatedText = this.gTranslateOps.translate("en", targetLanguage, srcText);

            model.addAttribute("text", srcText);
            model.addAttribute("translatedText", translatedText);
            model.addAttribute("targetLanguage", targetLanguage);
        } 
        
        catch (IOException ex) 
        {
            Logger.getLogger(TranslateController.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        catch (Exception ex) 
        {
            Logger.getLogger(TranslateController.class.getName()).log(Level.SEVERE, null, ex);
        }
              
        return viewName;
    }
    
    /**
     * Get all the supported languages for Google Translate API.
     * // TODO: Call method on the initialization of application
     * @return Mapping of language code and language name
     * @throws MalformedURLException
     * @throws IOException
     * @throws Exception 
     */
    private HashMap<String,String> getSupportedLanguages() throws MalformedURLException, IOException, Exception
    {
        HashMap<String,String> supportedLanguages = new HashMap<String,String>();
        String langCode = null;
        
        // Call Google Translate API to get supported languages 
        List<Language> languages = this.gTranslateOps.getSupportedLanguages();
        
        // Construct language mappings 
        for(Language language : languages)
        {
            langCode = language.getLanguage();
            
            switch(langCode)
            {
                case "af":
                    supportedLanguages.put("af", "Afrikaans");
                    break;
                case "sq":
                    supportedLanguages.put("sq", "Albanian");
                    break;
                case "ar":
                    supportedLanguages.put("ar", "Arabic");
                    break;
                case "az":
                    supportedLanguages.put("az", "Azerbaijani");
                    break;
                case "eu":
                    supportedLanguages.put("eu", "Basque");
                    break;
                case "bn":
                    supportedLanguages.put("bn", "Bengali");
                    break;
                case "be":
                    supportedLanguages.put("be", "Belarusian");
                    break;
                case "bg":
                    supportedLanguages.put("bg", "Bulgarian");
                    break;
                case "ca":
                    supportedLanguages.put("ca", "Catalan");
                    break;
                case "zh-CN":
                    supportedLanguages.put("zh-CN", "Chinese Simplified");
                    break;
                case "zh-TW":
                    supportedLanguages.put("zh-TW", "Chinese Traditional");
                    break;
                case "hr":
                    supportedLanguages.put("hr", "Croatian");
                    break;
                case "cs":
                    supportedLanguages.put("cs", "Czech");
                    break;
                case "da":
                    supportedLanguages.put("da", "Danish");
                    break;
                case "nl":
                    supportedLanguages.put("nl", "Dutch");
                    break;
                case "en":
                    supportedLanguages.put("en", "English");
                    break;
                case "eo":
                    supportedLanguages.put("eo", "Esperanto");
                    break;
                case "et":
                    supportedLanguages.put("et", "Estonian");
                    break;
                case "tl":
                    supportedLanguages.put("tl", "Filipino");
                    break;
                case "fi":
                    supportedLanguages.put("fi", "Finnish");
                    break;
                case "fr":
                    supportedLanguages.put("fr", "French");
                    break;
                case "gl":
                    supportedLanguages.put("eu", "Galician");
                    break;
                case "ka":
                    supportedLanguages.put("ka", "Georgian");
                    break;
                case "de":
                    supportedLanguages.put("de", "German");
                    break;
                case "el":
                    supportedLanguages.put("el", "Greek");
                    break;
                case "gu":
                    supportedLanguages.put("gu", "Gujarati");
                    break;
                case "ht":
                    supportedLanguages.put("ht", "Haitian Creole");
                    break;
                case "iw":
                    supportedLanguages.put("iw", "Hebrew");
                    break;
                case "hi":
                    supportedLanguages.put("hi", "Hindi");
                    break;
                case "hu":
                    supportedLanguages.put("hu", "Hungarian");
                    break;
                case "is":
                    supportedLanguages.put("is", "Icelandic");
                    break;
                case "id":
                    supportedLanguages.put("id", "Indonesian");
                    break;
                case "ga":
                    supportedLanguages.put("ga", "Irish");
                    break;
                case "it":
                    supportedLanguages.put("it", "Italian");
                    break;
                case "ja":
                    supportedLanguages.put("ja", "Japanese");
                    break;
                case "kn":
                    supportedLanguages.put("kn", "Kannada");
                    break;
                case "ko":
                    supportedLanguages.put("ko", "Korean");
                    break;
                case "la":
                    supportedLanguages.put("la", "Latin");
                    break;
                case "lv":
                    supportedLanguages.put("lv", "Latvian");
                    break;
                case "lt":
                    supportedLanguages.put("lt", "Lithuanian");
                    break;
                case "mk":
                    supportedLanguages.put("mk", "Macedonian");
                    break;
                case "ms":
                    supportedLanguages.put("ms", "Malay");
                    break;
                case "mt":
                    supportedLanguages.put("mt", "Maltese");
                    break;
                case "no":
                    supportedLanguages.put("no", "Norwegian");
                    break;
                case "fa":
                    supportedLanguages.put("fa", "Persian");
                    break;
                case "pl":
                    supportedLanguages.put("pl", "Polish");
                    break;
                case "pt":
                    supportedLanguages.put("pt", "Portuguese");
                    break;
                case "ro":
                    supportedLanguages.put("ro", "Romanian");
                    break;
                case "ru":
                    supportedLanguages.put("ru", "Russian");
                    break;
                case "sr":
                    supportedLanguages.put("sr", "Serbian");
                    break;
                case "sk":
                    supportedLanguages.put("sk", "Slovak");
                    break;
                case "sl":
                    supportedLanguages.put("sl", "Slovenian");
                    break;
                case "es":
                    supportedLanguages.put("es", "Spanish");
                    break;
                case "sw":
                    supportedLanguages.put("sw", "Swahili");
                    break;
                case "sv":
                    supportedLanguages.put("sv", "Swedish");
                    break;
                case "ta":
                    supportedLanguages.put("ta", "Tamil");
                    break;
                case "te":
                    supportedLanguages.put("te", "Telugu");
                    break;
                case "th":
                    supportedLanguages.put("th", "Thai");
                    break;
                case "tr":
                    supportedLanguages.put("tr", "Turkish");
                    break;
                case "uk":
                    supportedLanguages.put("uk", "Ukrainian");
                    break;
                case "ur":
                    supportedLanguages.put("ur", "Urdu");
                    break;
                case "vi":
                    supportedLanguages.put("vi", "Vietnamese");
                    break;
                case "cy":
                    supportedLanguages.put("cy", "Welsh");
                    break;
                case "yi":
                    supportedLanguages.put("yi", "Yiddish");
                    break;
                default:
                    Logger.getLogger(TranslateController.class.getName()).log(Level.WARNING, "Language Code {0} not supported.", new Object[]{langCode});
            }
        }
        
        return supportedLanguages;
    }
    
    /**
     * Sorts a map by the values
     * @param unsortMap
     * @return Sorted map by values
     */
    private Map<String, String> sortByComparator(Map<String, String> unsortMap)
    {
        // Convert Map to List
        List<Map.Entry<String, String>> list = new LinkedList<Map.Entry<String, String>>(unsortMap.entrySet());

        // Sort list with comparator, to compare the Map values
        Collections.sort(list, new Comparator<Map.Entry<String, String>>() 
        {
            @Override
            public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) 
            {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        // Convert sorted map back to a Map
        Map<String, String> sortedMap = new LinkedHashMap<String, String>();
        for(Iterator<Map.Entry<String, String>> it = list.iterator(); it.hasNext();)
        {
            Map.Entry<String, String> entry = it.next();
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        
        return sortedMap;
    }
}
