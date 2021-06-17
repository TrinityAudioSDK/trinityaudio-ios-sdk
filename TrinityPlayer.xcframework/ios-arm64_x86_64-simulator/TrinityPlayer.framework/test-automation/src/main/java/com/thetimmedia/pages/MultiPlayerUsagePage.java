package com.thetimmedia.pages;

import com.thetimmedia.fielddecorator.BasePage;
import com.thetimmedia.types.Section;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MultiPlayerUsagePage extends BasePage {

    @FindBy (xpath = ".//XCUIElementTypeOther[contains(@name,'audioFrame_')]")
    public List<Section> listOfPlayerInstances;
}
