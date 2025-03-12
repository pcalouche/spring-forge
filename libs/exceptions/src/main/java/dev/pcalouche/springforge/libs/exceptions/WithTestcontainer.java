package dev.pcalouche.springforge.libs.exceptions;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
public @interface WithTestcontainer {

	String[] containers();

	boolean applyCommonSnsTemplate() default false;

}
