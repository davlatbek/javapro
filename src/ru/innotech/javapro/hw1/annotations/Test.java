package ru.innotech.javapro.hw1.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Test {

    /**
     * Приоритет теста должен быть уникальным для каждого теста
     *
     * @return
     */
    int priority() default 5;

}
