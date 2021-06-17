package com.thetimmedia.fielddecorator;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.DefaultFieldDecorator;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import javax.annotation.Nullable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.util.List;

public class CustomFieldDecorator extends DefaultFieldDecorator {
    public CustomFieldDecorator(SearchContext searchContext) {
        super(new DefaultElementLocatorFactory(searchContext));
    }

    /**
     * called for each field in class
     */
    @Override
    public Object decorate(ClassLoader loader, Field field) {
        Class<?> decoratableClass = decoratableClass(field);
        // is decorateable
        if (decoratableClass != null) {
            ElementLocator locator = factory.createLocator(field);
            if (locator == null) {
                return null;
            }
            return createElement(loader, locator, decoratableClass, field, null);
        }
        return null;
    }

    public Object decorate(ClassLoader loader, Field field, @Nullable String name) {
        Class<?> decoratableClass = decoratableClass(field);
        if (decoratableClass != null) {
            ElementLocator locator = factory.createLocator(field);
            if (locator == null) {
                return null;
            }
            return createElement(loader, locator, decoratableClass, field, name);
        }
        return null;
    }

    /**
     *
     * @param field
     * @return field class or null if is not decoratable
     */
    private Class<?> decoratableClass(Field field) {
        Class<?> clazz = field.getType();
        if(!clazz.isAssignableFrom(List.class)) {
            try {
                clazz.getConstructor(WebElement.class);
            } catch (Exception e) {
                return null;
            }
        }
        return clazz;
    }

    /**
     * Element creation.
     * Search WebElement и send it to Custom Class
     */
    protected <T> T createElement(ClassLoader loader,
            ElementLocator locator, Class<T> clazz, Field field, @Nullable String parentName) {
        String name = "'"+field.getName().replaceAll(String.format("%s|%s|%s", "(?<=[A-Z])(?=[A-Z][a-z])",
                "(?<=[^A-Z])(?=[A-Z])", "(?<=[A-Za-z])(?=[^A-Za-z])"), " ")
                +"' on ";
        FindBy findBy = field.getAnnotation(FindBy.class);
        By by = buildByFromShortFindBy(findBy);
        if(parentName==null){
            name += field.getDeclaringClass().getSimpleName().replaceAll(String.format("%s|%s|%s", "(?<=[A-Z])(?=[A-Z][a-z])",
                    "(?<=[^A-Z])(?=[A-Z])", "(?<=[A-Za-z])(?=[^A-Za-z])"), " ");
        } else {
            name += parentName;
        }
        T result = null;
        if(clazz.isAssignableFrom(List.class)){
            Class listElements = CustomAbstractElement.class;
            for (Field f : field.getGenericType().getClass().getDeclaredFields()) {
                if(f.getName().equals("actualTypeArguments")){
                    try {
                        f.setAccessible(true);
                        listElements  = (Class)((Type[]) f.get(field.getGenericType()))[0];
                    } catch (Exception e){}
                    break;
                }
            }
            InvocationHandler handler = new ListInvocationHandler(locator, listElements, name, by);
            result = (T) Proxy.newProxyInstance(loader, new Class[]{List.class}, handler);
        } else {
            WebElement proxy = proxyForLocator(loader, locator);
            result = createInstance(clazz, proxy, name, by);
        }
        return result;
    }

    private <T> T createInstance(Class<T> clazz, WebElement element, String name, By by) {
        try {
            return (T) clazz.getConstructor(WebElement.class, String.class, By.class)
                    .newInstance(element, name, by);
        } catch (Exception e) {
            throw new AssertionError(
                    "WebElement '"+name+"' can't be represented as " + clazz
            );
        }
    }

    protected By buildByFromShortFindBy(FindBy findBy) {
        if (!"".equals(findBy.className())) {
            return By.className(findBy.className());
        } else if (!"".equals(findBy.css())) {
            return By.cssSelector(findBy.css());
        } else if (!"".equals(findBy.id())) {
            return By.id(findBy.id());
        } else if (!"".equals(findBy.linkText())) {
            return By.linkText(findBy.linkText());
        } else if (!"".equals(findBy.name())) {
            return By.name(findBy.name());
        } else if (!"".equals(findBy.partialLinkText())) {
            return By.partialLinkText(findBy.partialLinkText());
        } else if (!"".equals(findBy.tagName())) {
            return By.tagName(findBy.tagName());
        } else {
            return !"".equals(findBy.xpath()) ? By.xpath(findBy.xpath()) : null;
        }
    }
}
