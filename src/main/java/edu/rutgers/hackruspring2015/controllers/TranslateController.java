package edu.rutgers.hackruspring2015.controllers;

import edu.rutgers.hackruspring2015.models.TranslateForm;
import java.util.Map;
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
        TranslateForm translateForm = new TranslateForm();
        String viewName = "translate";
        String commandName = "userTranslateForm";
        return new ModelAndView(viewName, commandName, translateForm);
    }
     
    @RequestMapping(value="/translate/process", method = RequestMethod.POST)
    public String processTranslationForm(@ModelAttribute("userTranslateForm") TranslateForm formObj, ModelMap model)
    {
        model.addAttribute("content", formObj.getContent());
        String viewName = "result";
        return viewName;
    }
}
