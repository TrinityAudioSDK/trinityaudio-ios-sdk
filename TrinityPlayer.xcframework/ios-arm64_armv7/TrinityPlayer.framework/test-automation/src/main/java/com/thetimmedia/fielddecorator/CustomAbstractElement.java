package com.thetimmedia.fielddecorator;

import com.thetimmedia.driver.Driver;
import com.thetimmedia.enums.NativeAppAttributesEnum;
import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.internal.WrapsElement;

/**
 * Abstract element. All types in com.thetimmedia.types should inherit this class for being initialized
 * <p>
 * Contains methods applicable for all types
 */
public abstract class CustomAbstractElement implements WrapsElement {
    protected WebElement webElement;
    protected By by;
    /**
     * Name to be shown in Allure reporting. Sets automatically during initialization.
     */
    protected String name = "SET ELEMENT NAME";

    public CustomAbstractElement(WebElement webElement) {
        this.webElement = webElement;
    }

    public CustomAbstractElement(WebElement element, String name, By by) {
        this.webElement = element;
        this.name = name;
        this.by = by;
    }

    public CustomAbstractElement(By by) {
        this.webElement = Driver.getDriver().findElement(by);
        setName();
    }

    public CustomAbstractElement(By by, String name) {
        this.webElement = Driver.getDriver().findElement(by);
        this.name = name;
    }

    public CustomAbstractElement() {
    }

    public By getBy() {
        return by;
    }

    public void setBy(By by) {
        this.by = by;
    }

    public String getName() {
        return name;
    }

    private void setName() {
        final StackTraceElement[] ste = Thread.currentThread().getStackTrace();
        this.name = ste[4].getMethodName();
    }

    public void clickUsingJS() {
        try {
            Driver.getDriver().executeScript("arguments[0].click();",
                    this.getWrappedElement().findElement(By.xpath(".//self::*")));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Element " + this.getName() + " wasn't clicked using JS.\n" + e.getMessage());
        }
    }

    public void clickUsingActions() {
        try {
            Actions action = new Actions(Driver.getDriver());
            action.moveToElement(this.getWrappedElement());
            action.click();
            action.perform();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Element " + this.getName() + " wasn't clicked using Actions.\n" + e.getMessage());
        }
    }

    public void click() {
        click(this.getName());
    }

    @Step("Tap on {arg0}")
    private void click(String elementName) {
        String name = elementName;
        try {
            if ("NATIVE_APP".equals(Driver.getDriver().getContext())) {
                clickUsingActions();
            } else {
                this.getWrappedElement().findElement(By.xpath(".//self::*")).click();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(name + " wasn't clicked . Reason is: " + e.getCause() + " " + e.getMessage());
        }
    }

    /**
     * Check attribute "visible" for native iOS elements and isDisplayed for wrapped elements in WebView
     *
     * @return is element visible
     */
    public boolean isDisplayed() {
        return isDisplayed(true);
    }

    public boolean isDisplayed(boolean isWithWait) {
        if (!isWithWait) {
            Driver.setImplicitlyWait(0);
        }
        boolean isDisplayed = false;
        try {
            if ("NATIVE_APP".equals(Driver.getDriver().getContext())) {
                if (this == null || this.getIOSAttribute(NativeAppAttributesEnum.visible).equals("true")) {
                    isDisplayed = true;
                }
            } else {
                if (this.getWrappedElement() != null) {
                    try {
                        isDisplayed = Driver.getDriver().findElement(this.getBy()).isDisplayed();
                    } catch (NoSuchElementException e) {
                        isDisplayed = false;
                    }
                }
            }
        } catch (NoSuchElementException e) {
            isDisplayed = false;
        }
        if (isWithWait) {
            Driver.setDefaultImplicitlyWait();
        }
        return isDisplayed;
    }

    @Override
    public WebElement getWrappedElement() {
        return webElement;
    }

    public String getAttribute(String attribute) {
        return this.getWrappedElement().getAttribute(attribute);
    }

    public String getIOSAttribute(NativeAppAttributesEnum attribute) {
        return this.getWrappedElement().getAttribute(attribute.toString());
    }

    public String isEnabled(String attribute) {
        return this.getWrappedElement().getAttribute(attribute);
    }

    public boolean isSelected() {
        return this.getWrappedElement().isSelected();
    }

    public String getText() {
        if ("NATIVE_APP".equals(Driver.getDriver().getContext())) {
            if (this.getWrappedElement() == null) {
                return this.getIOSAttribute(NativeAppAttributesEnum.label);
            } else {
                return "";
            }
        } else {
            if (this.getWrappedElement() != null) {
                return this.getAttribute("textContent");
            } else {
                return "";
            }
        }
    }
}
