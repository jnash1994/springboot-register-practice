package Nash.example.register.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

// 該註解用在類別上
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
// @Constraint註解的validatedBy元素指定實作驗證邏輯的驗證器類別
@Constraint(validatedBy = FieldMatchValidator.class)
public @interface FieldMatch {
    String message() default "first和second必须相等";
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
    String first();
    String second();
}

