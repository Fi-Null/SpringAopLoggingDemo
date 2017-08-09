package com.springAop.demo.domain.annotation;

import java.lang.annotation.*;

@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface AopCallSuccessJudge {
	String value() default "";
}
