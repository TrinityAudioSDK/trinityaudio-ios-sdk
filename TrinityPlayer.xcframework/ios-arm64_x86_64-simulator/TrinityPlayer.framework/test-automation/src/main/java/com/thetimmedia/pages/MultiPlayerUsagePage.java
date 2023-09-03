package com.thetimmedia.pages;

import com.thetimmedia.fielddecorator.BasePage;
import com.thetimmedia.types.Section;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MultiPlayerUsagePage extends BasePage {

    @FindBy (xpath = "//XCUIElementTypeOther[@name='Trinity Audio Player']//XCUIElementTypeButton[@name='Play']")
    public List<Section> listOfPlayerInstances;
}
