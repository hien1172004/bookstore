package ptit.example.btlwebbook.contraints;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
@Constraint(validatedBy = EnumPatternValidator.class)
public @interface EnumPattern {
    String name();
    String regexp();
    String message() default "{name} must be {regexp}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
