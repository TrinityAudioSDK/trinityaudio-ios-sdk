package com.thetimmedia.types;

import com.thetimmedia.enums.NativeAppAttributesEnum;
import com.thetimmedia.fielddecorator.CustomAbstractElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Toggle extends CustomAbstractElement {
    public Toggle(WebElement webElement) {
        super(webElement);
    }

    public Toggle(WebElement element, String name, By by) {
        super( element, name, by);
    }

    public void enable() {
        if (!"1".equals(this.getIOSAttribute(NativeAppAttributesEnum.value))) {
            this.click();
        }
    }

    public void disable() {
        if (!"0".equals(this.getIOSAttribute(NativeAppAttributesEnum.value))) {
            this.click();
        }
    }
}
