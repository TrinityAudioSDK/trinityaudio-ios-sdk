package com.thetimmedia.pages;

import com.thetimmedia.fielddecorator.BasePage;
import com.thetimmedia.pages.blocks.PlayerBlock;
import com.thetimmedia.pages.blocks.PlayerSettingsBlock;
import com.thetimmedia.types.Link;
import org.openqa.selenium.support.FindBy;

public class MainSDKUsagePage extends BasePage {

    public PlayerBlock playerBlock = new PlayerBlock();
    public PlayerSettingsBlock settingsBlock = new PlayerSettingsBlock();

    @FindBy(xpath = "//XCUIElementTypeButton[@label='Back']")
    private Link backLink;

    @FindBy(xpath = "//XCUIElementTypeOther")
    private Link fabButton;
}
