package com.thetimmedia.types;

import com.thetimmedia.fielddecorator.CustomAbstractElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Button extends CustomAbstractElement {
    public Button(WebElement webElement) {
        super(webElement);
    }

    public Button(WebElement element, String name, By by) {
        super( element, name, by);
    }

}
