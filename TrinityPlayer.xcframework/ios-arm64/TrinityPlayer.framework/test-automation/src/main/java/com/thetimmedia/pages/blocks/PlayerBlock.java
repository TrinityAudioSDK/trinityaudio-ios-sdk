package com.thetimmedia.pages.blocks;

import com.thetimmedia.fielddecorator.BasePage;
import com.thetimmedia.fielddecorator.CustomAbstractElement;
import com.thetimmedia.types.Button;
import com.thetimmedia.types.Label;
import com.thetimmedia.utils.CommonSteps;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

@Component
public class PlayerBlock extends BasePage {

    @FindBy(xpath = "(//XCUIElementTypeOther[@name='Trinity Audio Player'])[2]")
    public Label playerFrame;

    @FindBy(xpath = "(//XCUIElementTypeOther[@name='Trinity Audio Player'])[2]/XCUIElementTypeButton[1]")
    public Button playStopNativeButton;

    @FindBy(xpath = ".//XCUIElementTypeScrollView/following-sibling::XCUIElementTypeOther[//XCUIElementTypeButton[@name='Play']]")
    public Button playStopFABNativeButton;

    @FindBy(xpath = ".//button[@class='button button-play']")
    public Button playWebButton;

    @FindBy(xpath = ".//button[@class='button button-pause']")
    public Button pauseWebButton;

    @FindBy(xpath = ".//div[@class='title']/span")
    public Label titleWebLabel;

    @FindBy(xpath = ".//div[@class='timer']")
    public Label timerWebLabel;

    @FindBy(xpath = ".//div[@class='powered-by']/span")
    public Label poweredByWebLabel;

    @FindBy(xpath = ".//div[@class='translation-language']")
    public Label translationLanguage;

    @FindBy(xpath = ".//div[@class='powered-by']/a")
    public Label poweredByWhomWebLabel;

    @FindBy(xpath = ".//button[contains(@class,'settings-button')]")
    public Button languageSettingsWebButton;

    @FindBy(xpath = "//XCUIElementTypeButton[@name='Settings']")
    public Button languageSettingsNativeButton;

    @FindBy(xpath = ".//button[@class='button button-loading']")
    public Button loadingWebButton;

    @FindBy(xpath = ".//div[contains(@class, 'cmp-el-progress-bar')]")
    public Label progressWebBar;

    @FindBy(xpath = ".//div[@class='display-ad-wrapper']/iframe[@srcdoc]")
    public Label advertisementWebBlock;

    public PlayerLanguageSelectBlock getLanguageSelectBlock () {
        CommonSteps.switchToMainApp();
        languageSettingsNativeButton.click();
        return new PlayerLanguageSelectBlock();
    }

    public void clickOnPlayStopFABButton () {
        clickOnPlayStopFABButton(playStopFABNativeButton);
    }
    @Step ("Click on {arg0.name} button")
    private void clickOnPlayStopFABButton (CustomAbstractElement element) {
        element.getWrappedElement().click();
    }
}
