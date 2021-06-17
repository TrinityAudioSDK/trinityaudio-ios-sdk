package com.thetimmedia.pages.blocks;

import com.thetimmedia.fielddecorator.BasePage;
import com.thetimmedia.types.Button;
import com.thetimmedia.types.Link;
import com.thetimmedia.types.Select;
import com.thetimmedia.types.Toggle;
import org.openqa.selenium.support.FindBy;

public class PlayerSettingsBlock extends BasePage {

    @FindBy(xpath = "//XCUIElementTypeStaticText[@name='Powered by']/following-sibling::XCUIElementTypeSwitch")
    public Toggle poweredByToggle;

    @FindBy(xpath = "//XCUIElementTypeStaticText[@name='Language']/following-sibling::*")
    public Select languageSelect;

    @FindBy(xpath = "//XCUIElementTypeStaticText[@name='Voice gender']/following-sibling::*")
    public Select voiceGenderSelect;

    @FindBy(xpath = "//XCUIElementTypeStaticText[@name='Voice id']/following-sibling::*")
    public Select voiceIdSelect;

    @FindBy(xpath = "//XCUIElementTypeButton[@name='Trinity']")
    public Button trinityButton;

    @FindBy(xpath = "//XCUIElementTypeButton[@name='Trinity with display Ad']")
    public Button trinityWithDisplayAddButton;

    @FindBy(xpath = "//XCUIElementTypeButton[@label='Apply']")
    public Link applyLink;

}
