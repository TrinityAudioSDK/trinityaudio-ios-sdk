package com.thetimmedia.types;

import com.thetimmedia.fielddecorator.CustomAbstractElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Link extends CustomAbstractElement {
    public Link(WebElement webElement) {
        super(webElement);
    }

    public Link(WebElement element, String name, By by) {
        super( element, name, by);
    }

    public String getLinkSource () {
        return this.getAttribute("src");
    }
}
