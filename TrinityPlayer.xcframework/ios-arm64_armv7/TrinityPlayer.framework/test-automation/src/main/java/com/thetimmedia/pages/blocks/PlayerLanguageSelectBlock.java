package com.thetimmedia.pages.blocks;

import com.thetimmedia.fielddecorator.BasePage;
import com.thetimmedia.types.Button;
import com.thetimmedia.utils.CommonSteps;
import org.openqa.selenium.support.FindBy;
import sun.security.util.PendingException;

public class PlayerLanguageSelectBlock extends BasePage {

    @FindBy(xpath = ".//span[@data-id='en']")
    private Button englishWebButton;

    @FindBy(xpath = ".//span[@data-id='de']")
    private Button germanWebButton;

    @FindBy(xpath = ".//span[@data-id='fr']")
    private Button frenchWebButton;

    @FindBy(xpath = ".//span[@data-id='es']")
    private Button spanishWebButton;

    @FindBy(xpath = ".//span[@data-id='zh']")
    private Button chineseWebButton;

    @FindBy(xpath = ".//span[@data-id='it']")
    private Button italianWebButton;

    @FindBy(xpath = ".//div[@class='cancel']")
    public Button cancelWebButton;

    @FindBy(xpath = "//XCUIElementTypeStaticText[@value='ENGLISH']/parent::*")
    private Button englishNativeButton;

    @FindBy(xpath = "//XCUIElementTypeStaticText[@value='DEUTSCH']/parent::*")
    private Button germanNativeButton;

    @FindBy(xpath = "//XCUIElementTypeStaticText[@value='FRANÇAIS']/parent::*")
    private Button frenchNativeButton;

    @FindBy(xpath = "//XCUIElementTypeStaticText[@value='ESPAÑOL']/parent::*")
    private Button spanishNativeButton;

    @FindBy(xpath = "//XCUIElementTypeStaticText[@value='汉语']/parent::*")
    private Button chineseNativeButton;

    @FindBy(xpath = "//XCUIElementTypeStaticText[@value='ITALIANO']/parent::*")
    private Button italianNativeButton;

    public Button getWebButtonByLanguage(String language) {
        CommonSteps.switchToFrameIfNeeded();
        switch (language) {
        case "English":
            return englishWebButton;
        case "German":
            return germanWebButton;
        case "French":
            return frenchWebButton;
        case "Spanish":
            return spanishWebButton;
        case "Chinese":
            return chineseWebButton;
        case "Italian":
            return italianWebButton;
        default: throw new PendingException("Script error: Please define '"+language+"' language button in " + this.getClass().getSimpleName());
        }
    }

    public Button getNativeButtonByLanguage(String language) {
        CommonSteps.switchToMainApp();
        switch (language) {
        case "English":
            return englishNativeButton;
        case "German":
            return germanNativeButton;
        case "French":
            return frenchNativeButton;
        case "Spanish":
            return spanishNativeButton;
        case "Chinese":
            return chineseNativeButton;
        case "Italian":
            return italianNativeButton;
        default: throw new PendingException("Script error: Please define '"+language+"' language button in " + this.getClass().getSimpleName());
        }
    }

}
