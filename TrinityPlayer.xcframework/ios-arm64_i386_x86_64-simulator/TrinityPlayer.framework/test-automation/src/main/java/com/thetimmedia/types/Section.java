package com.thetimmedia.types;

import com.thetimmedia.fielddecorator.CustomAbstractElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Section extends CustomAbstractElement {
    public Section(WebElement webElement) {
        super(webElement);
    }

    public Section(WebElement element, String name, By by) {
        super( element, name, by);
    }
}
