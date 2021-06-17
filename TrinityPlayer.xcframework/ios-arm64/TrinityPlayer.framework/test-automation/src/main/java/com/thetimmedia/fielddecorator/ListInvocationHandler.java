package com.thetimmedia.fielddecorator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ListInvocationHandler<T extends CustomAbstractElement> implements InvocationHandler {
    private final ElementLocator locator;
    private final Class<T> clazz;
    private final String name;
    private final By by;

    public ListInvocationHandler
            (ElementLocator elementLocator, Class<T> htmlType, String name, By by) {
        locator = elementLocator;
        this.clazz = htmlType;
        this.name = name;
        this.by = by;
    }

    /**
     * Looking list of web Elements, process it and returns
     * new list with Custom type elements
     *
     * @param object
     * @param method
     * @param objects
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object object, Method method, Object[] objects) throws Throwable {

        List<WebElement> list = locator.findElements();
        List<T> toReturn = new ArrayList<>();
        int i = 0;
        for (WebElement e : list) {
            toReturn.add(FactoryUtils.createNewInstance(clazz, e, name + i, by));
            i++;
        }
        try {
            return method.invoke(toReturn, objects);
        } catch (InvocationTargetException e) {
            throw e.getCause();
        }
    }
}
