package ru.innotech.javapro.hw1.util;

import ru.innotech.javapro.hw1.annotations.AfterSuite;
import ru.innotech.javapro.hw1.annotations.BeforeSuite;
import ru.innotech.javapro.hw1.annotations.Test;
import ru.innotech.javapro.hw1.tests.MyTests;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

public class TestRunner {

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, InstantiationException {
        TestRunner.runTests(MyTests.class);
    }

    public static void runTests(Class c) throws InvocationTargetException, IllegalAccessException, InstantiationException {
        Object myTestsObject = c.newInstance();
        runBeforeSuiteMethod(c, myTestsObject);
        runTestMethods(myTestsObject);
        runAfterSuiteMethod(c, myTestsObject);
    }

    private static void runBeforeSuiteMethod(Class c, Object myTestsObject) throws IllegalAccessException, InvocationTargetException {
        if (isBeforeAndAfterSuiteAnnotationCountLimitExceeded(myTestsObject.getClass(), BeforeSuite.class)) {
            throw new IllegalArgumentException("Количество методов, помеченных аннотацией BeforeSuite должно быть равно 1");
        }
        Method[] methods = c.getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(BeforeSuite.class)) {
                System.out.println("Запуск beforesuite метода");
                method.invoke(myTestsObject, null);
                System.out.println("Конец beforesuite метода");
            }
        }
    }

    private static void runTestMethods(Object myTestsObject) throws IllegalAccessException, InvocationTargetException {
        HashMap<Integer, Method> methodToPriorityMap = getMethodToPriorityMap(myTestsObject.getClass());
        for (int i = 0; i <= 10; i++) {
            if (methodToPriorityMap.containsKey(i)) {
                System.out.println("-Запуск теста " + methodToPriorityMap.get(i).getName());
                methodToPriorityMap.get(i).invoke(myTestsObject, null);
                System.out.println("-Конец теста " + methodToPriorityMap.get(i).getName());
            }
        }
    }

    private static void runAfterSuiteMethod(Class c, Object myTestsObject) throws InvocationTargetException, IllegalAccessException {
        if (isBeforeAndAfterSuiteAnnotationCountLimitExceeded(myTestsObject.getClass(), AfterSuite.class)) {
            throw new IllegalArgumentException("Количество методов, помеченных аннотацией AfterSuite должно быть равно 1");
        }
        Method[] methods = c.getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(AfterSuite.class)) {
                System.out.println("Запуск aftersuite метода");
                method.invoke(myTestsObject, null);
                System.out.println("Конец aftersuite метода");
            }
        }
    }

    private static HashMap<Integer, Method> getMethodToPriorityMap(Class c) {
        HashMap<Integer, Method> priorities = new HashMap<>();
        Method[] methods = c.getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Test.class)) {
                Test testAnnotation = method.getAnnotation(Test.class);
                int priority = testAnnotation.priority();
                if (priority > 10) {
                    throw new IllegalArgumentException("Приоритет теста не может быть больше 10");
                }
                priorities.put(priority, method);
            }
        }
        return priorities;
    }

    /**
     * Проверка аннотаций BeforeSuite и AfterSuite
     */
    private static boolean isBeforeAndAfterSuiteAnnotationCountLimitExceeded(Class c, Class annotationClass) {
        Method[] methods = c.getDeclaredMethods();
        int count = 0;
        for (Method method : methods) {
            if (method.isAnnotationPresent(annotationClass)) {
                count++;
            }
            if (count > 1) {
                return true;
            }
        }
        return false;
    }


}
