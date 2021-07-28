package com.thetimmedia.types;

import com.thetimmedia.fielddecorator.CustomAbstractElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Label extends CustomAbstractElement {
    public Label(WebElement webElement) {
        super(webElement);
    }

    public Label(WebElement element, String name, By by) {
        super( element, name, by);
    }

}
