package edu.rutgers.hackruspring2015.controllers;

import edu.rutgers.hackruspring2015.GoogleTranslate;
import edu.rutgers.hackruspring2015.models.TranslateForm;
import java.io.IOException;
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
   /*@RequestMapping("/translate")
   public ModelAndView helloWorld() 
   {
     String message = "<br><div style='text-align:center;'>"
				+ "<h3>********** Hello World, Spring MVC Tutorial</h3>This message is coming from CrunchifyHelloWorld.java **********</div><br><br>";
		return new ModelAndView("translate", "message", message);
   }*/
    
    @RequestMapping(value="/translate", method = RequestMethod.GET)
    public ModelAndView viewTranslateForm() 
    {
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
            
            // Google API to translate
            GoogleTranslate gTranslateOps = new GoogleTranslate("AIzaSyBlEDmdkVmMLr0rE0DxkuDY-KEnP873EbE"); 
            String translatedText = gTranslateOps.translate("en", "es", srcText);

            model.addAttribute("text", srcText);
            model.addAttribute("translatedText", translatedText);
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
}
