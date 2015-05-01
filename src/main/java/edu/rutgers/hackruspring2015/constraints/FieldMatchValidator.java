package edu.rutgers.hackruspring2015.constraints;

import edu.rutgers.hackruspring2015.controllers.TranslateController;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.beanutils.BeanUtils;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object>
{
    private String firstFieldName;
    private String secondFieldName;

    @Override
    public void initialize(final FieldMatch constraintAnnotation)
    {
        firstFieldName = constraintAnnotation.first();
        secondFieldName = constraintAnnotation.second();
    }

    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context)
    {
        try
        {
            final Object firstObj = BeanUtils.getProperty(value, firstFieldName);
            final Object secondObj = BeanUtils.getProperty(value, secondFieldName);
            
            return firstObj == null && secondObj == null || firstObj != null && !firstObj.equals(secondObj);
        }
        catch (Exception ex)
        {
            Logger.getLogger(TranslateController.class.getName()).log(Level.SEVERE, "", ex);
        }
        return true;
    }
}
