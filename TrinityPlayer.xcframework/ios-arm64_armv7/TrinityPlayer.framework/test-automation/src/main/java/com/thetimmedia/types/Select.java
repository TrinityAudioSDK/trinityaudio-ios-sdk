package com.thetimmedia.types;

import com.thetimmedia.driver.Driver;
import com.thetimmedia.fielddecorator.CustomAbstractElement;
import com.thetimmedia.utils.Assertions;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Select extends CustomAbstractElement {
    public Select(WebElement webElement) {
        super(webElement);
    }

    public Select(WebElement element, String name, By by) {
        super(element, name, by);
    }

    public void selectByVisibleText(String text) {
        selectByVisibleText(text, this.getName());
    }

    @Step ("Select {arg0} option from {arg1} select")
    private void selectByVisibleText(String text, String selectName) {
        this.click();
        Driver.getDriver().findElement(By.xpath("//XCUIElementTypePickerWheel")).sendKeys(text);
        Driver.getDriver().findElement(By.xpath("//XCUIElementTypeToolbar//XCUIElementTypeButton[@label='Done']"))
                .click();
        Assertions.expectedStringsAreEqual(selectName, this.getAttribute("value"), text);
    }

    public List<String> getAllOptionsAsString() {
        List toReturn = new ArrayList();
        this.click();
        WebElement pickerElement = Driver.getDriver().findElement(By.xpath("//XCUIElementTypePickerWheel"));
        toReturn.add(pickerElement.getAttribute("value"));
        while (true) {
            Map<String, Object> params = new HashMap<>();
            try {
                params.put("order", "next");
                params.put("offset", "0.1");
                params.put("element", ((RemoteWebElement) pickerElement).getId());
                Driver.getDriver().executeScript("mobile: selectPickerWheelValue", params);
                toReturn.add(pickerElement.getAttribute("value"));
            } catch (Exception e) {
                break;
            }
        }
        Driver.getDriver().findElement(By.xpath("//XCUIElementTypeToolbar//XCUIElementTypeButton[@label='Done']"))
                .click();
        return toReturn;
    }
}
