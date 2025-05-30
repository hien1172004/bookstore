package ptit.example.btlwebbook.contraints;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.lang.annotation.Annotation;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EnumPatternValidator implements ConstraintValidator<EnumPattern, Enum<?>> {
    private Pattern pattern;
    @Override
    public void initialize(EnumPattern constraintAnnotation) {
        try{
            pattern = Pattern.compile(constraintAnnotation.regexp());
        } catch (Exception e) {
            throw new IllegalArgumentException("Given regex valid", e);
        }
    }

    @Override
    public boolean isValid(Enum<?> value, ConstraintValidatorContext constraintValidatorContext) {
        if(value == null) return true;
        Matcher matcher = pattern.matcher(value.name());
        return matcher.matches();

    }
}
