package com.thetimmedia.fielddecorator;

import org.apache.commons.lang3.reflect.ConstructorUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.lang.reflect.*;

public class FactoryUtils {

    /**
     * Type erasure in Java isn't complete. Attempt to discover the generic
     * interfaceType of the list.
     *
     * @param field
     * @return Class<?>
     */
    public static Class<?> getErasureClass(Field field) {

        Type genericType = field.getGenericType();
        if (!(genericType instanceof ParameterizedType)) {
            return null;
        }
        return (Class<?>) ((ParameterizedType) genericType).getActualTypeArguments()[0];
    }

    /**
     * Creates class instance, that implement Class<T> clazz
     * and invoke constructor with WebElement argument
     *
     * @param clazz   class
     * @param element WebElement element
     * @param <T>     custom web Element
     * @return <T extends Element> T
     */
    public static <T extends CustomAbstractElement> T createNewInstance(Class<T> clazz, WebElement element) {
        T instance = null;

        Constructor<?> cons = ConstructorUtils.getAccessibleConstructor(clazz, WebElement.class);

        try {
            instance = (T) cons.newInstance(element);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return instance;
    }

    public static <T extends CustomAbstractElement> T createNewInstance(Class<T> clazz, WebElement element, String name, By by) {
        T instance = null;

        Constructor<?> cons = null;
        try {
            cons = clazz.getConstructor(WebElement.class, String.class, By.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        if (cons == null) {
            try {
                return createNewInstance(clazz, element);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            try {
                instance = (T) cons.newInstance(element, name, by);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }
}
