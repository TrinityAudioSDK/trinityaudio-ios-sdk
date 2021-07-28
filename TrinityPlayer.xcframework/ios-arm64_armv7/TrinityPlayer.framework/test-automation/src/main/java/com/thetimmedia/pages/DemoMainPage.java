package com.thetimmedia.pages;

import com.thetimmedia.fielddecorator.BasePage;
import com.thetimmedia.types.Link;
import org.openqa.selenium.support.FindBy;

public class DemoMainPage extends BasePage {

    @FindBy(xpath = "//XCUIElementTypeButton[@label='Main SDK Usage']")
    public Link mainSDKUsageLink;

    @FindBy(xpath = "//XCUIElementTypeButton[@label='Multiple players usage']")
    public Link multiplePlayerUsageLink;

    @FindBy(xpath = "//XCUIElementTypeButton[@label='Cast player']")
    public Link castPlayerLink;

    public void clickOnLink (String link) {
        switch (link) {
        case "Main SDK Usage":
            mainSDKUsageLink.click();
            break;
        case "Multiple players usage":
            multiplePlayerUsageLink.click();
            break;
        case "Cast player":
            castPlayerLink.click();
            break;
        default: throw new AssertionError("Please Define Case for '"+link+ "'");
        }
    }

}
