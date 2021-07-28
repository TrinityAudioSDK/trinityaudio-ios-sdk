package com.thetimmedia.fielddecorator;

import com.thetimmedia.driver.Driver;
import org.openqa.selenium.support.PageFactory;

/**
 * All Page Object pages should inherit this class
 */
public class BasePage {
    /**
     * init all elements(com.thetimmedia.types) on the page
     */
    public BasePage() {
        PageFactory.initElements(new CustomFieldDecorator(Driver.getDriver()), this);
    }
}
