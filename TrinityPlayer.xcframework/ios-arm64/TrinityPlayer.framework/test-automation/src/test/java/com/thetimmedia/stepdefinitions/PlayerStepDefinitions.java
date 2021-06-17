package com.thetimmedia.stepdefinitions;

import com.thetimmedia.driver.Driver;
import com.thetimmedia.pages.blocks.PlayerBlock;
import com.thetimmedia.utils.Assertions;
import com.thetimmedia.utils.CommonSteps;
import com.thetimmedia.utils.ProjectPropertiesUtils;
import com.thetimmedia.utils.Waiters;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class PlayerStepDefinitions {

    @And("^User is able to play article$")
    public void userIsAbleToPlayArticle() {
        PlayerBlock playerBlock = new PlayerBlock();
        playerBlock.playStopNativeButton.click();
        userVerifiesPlaybackIsStarted();
        CommonSteps.screenshot();
    }

    @And("^User verifies \"([^\"]*)\" button is \"(displayed|not displayed)\" in player$")
    public void userVerifiesButtonIsInPlayer(String button, String displayed) {
        boolean isShouldBeDisplayed = "displayed".equals(displayed) ? true : false;
        CommonSteps.switchToFrameIfNeeded();
        PlayerBlock playerBlock = new PlayerBlock();
        switch (button) {
        case "Play":
            if (isShouldBeDisplayed) {
                Assertions.elementIsVisible(playerBlock.playWebButton);
            } else {
                Assertions.elementIsNOTVisible(playerBlock.playWebButton);
            }
            break;
        case "Pause":
            if (isShouldBeDisplayed) {
                Assertions.elementIsVisible(playerBlock.pauseWebButton);
            } else {
                Assertions.elementIsNOTVisible(playerBlock.pauseWebButton);
            }
            break;
        case "Settings":
            if (isShouldBeDisplayed) {
                Assertions.elementIsVisible(playerBlock.languageSettingsWebButton);
            } else {
                Assertions.elementIsNOTVisible(playerBlock.languageSettingsWebButton);
            }
            break;
        default:
            throw new PendingException("Please define step for " + button + " button");
        }
        CommonSteps.switchToMainApp();
        CommonSteps.screenshot();
    }

    @And("^User verifies 'Progress' bar is \"(displayed|not displayed)\" in player$")
    public void userVerifiesBarIsInPlayer(String displayed) {
        boolean isShouldBeDisplayed = "displayed".equals(displayed) ? true : false;
        CommonSteps.switchToFrameIfNeeded();
        PlayerBlock playerBlock = new PlayerBlock();
        if (isShouldBeDisplayed) {
            Assertions.elementIsVisible(playerBlock.progressWebBar);
        } else {
            Assertions.elementIsNOTVisible(playerBlock.progressWebBar);
        }
        CommonSteps.switchToMainApp();
    }

    @And("^User verifies \"([^\"]*)\" label contains \"([^\"]*)\" text in player$")
    public void userVerifiesLabelContainsTextInPlayer(String labelName, String text) {
        CommonSteps.switchToFrameIfNeeded();
        PlayerBlock playerBlock = new PlayerBlock();
        switch (labelName) {
        case "Title":
            // wait for ad playing completed
            Waiters.waitForTextToBe(20, playerBlock.titleWebLabel, text);
            Assertions.elementContainsText(playerBlock.titleWebLabel, text);
            break;
        case "Powered By":
            String powerBy = playerBlock.poweredByWebLabel.getText();
            String powerByWhom = playerBlock.poweredByWhomWebLabel.getText();
            Assertions.expectedStringsAreEqual(playerBlock.poweredByWebLabel.getName(), powerBy + " " + powerByWhom,
                    text);
            break;
        case "Timer":
            Assertions.elementContainsText(playerBlock.timerWebLabel, text);
            break;
        default:
            throw new PendingException("Implement case for '" + labelName + "' label");
        }
        CommonSteps.switchToMainApp();
        CommonSteps.screenshot();
    }

    @And("^User verifies \"([^\"]*)\" label not contains \"([^\"]*)\" value in player$")
    public void userVerifiesLabelNotContainsValueInPlayer(String labelName, String text) {
        CommonSteps.switchToFrameIfNeeded();
        PlayerBlock playerBlock = new PlayerBlock();
        switch (labelName) {
        case "Title":
            Assertions.elementNOTContainsText(playerBlock.titleWebLabel, text);
            break;
        case "Powered By":
            String powerBy = playerBlock.poweredByWebLabel.getText();
            String powerByWhom = playerBlock.poweredByWhomWebLabel.getText();
            Assertions.expectedStringsAreNOTEqual(playerBlock.poweredByWebLabel.getName(), powerBy + " " + powerByWhom,
                    text);
            break;
        case "Timer":
            if ("zero".toLowerCase().equals(text.toLowerCase())) {
                Assertions.elementTextIsNotZero(playerBlock.timerWebLabel);
            } else {
                Assertions.elementNOTContainsText(playerBlock.timerWebLabel, text);
            }
            break;
        default:
            throw new PendingException("Implement case for '" + labelName + "' label");
        }
        CommonSteps.switchToMainApp();
    }

    @When("^User taps \"(Play|Pause|Settings)\" button in player$")
    public void userTapsButtonInPlayer(String button) {
        CommonSteps.switchToMainApp();
        PlayerBlock playerBlock = new PlayerBlock();
        playerBlock.playStopNativeButton.click();
        switch (button) {
        case "Play":
            CommonSteps.switchToFrameIfNeeded();
            Waiters.waitAppearanceOf(Integer.parseInt(ProjectPropertiesUtils.getInstance().getDefaultTimeout()),
                    playerBlock.pauseWebButton);
            CommonSteps.switchToMainApp();
            break;
        case "Pause":
            CommonSteps.switchToFrameIfNeeded();
            Waiters.waitAppearanceOf(Integer.parseInt(ProjectPropertiesUtils.getInstance().getDefaultTimeout()),
                    playerBlock.playWebButton);
            CommonSteps.switchToMainApp();
            break;
        case "Settings":
            CommonSteps.switchToFrameIfNeeded();
            playerBlock.languageSettingsWebButton.click();
            break;
        }

    }

    @When("^User taps \"(Play|Pause)\" button on Fab element$")
    public void userTapButtonOnFabElement(String button) {
        CommonSteps.switchToMainApp();
        PlayerBlock playerBlock = new PlayerBlock();
        playerBlock.clickOnPlayStopFABButton();
        // Verify that click performed and button state is changed
        CommonSteps.switchToFrameIfNeeded();
        switch (button) {
        case "Play":
            Assertions.elementIsVisible(playerBlock.pauseWebButton);
            break;
        case "Pause":
            Assertions.elementIsVisible(playerBlock.playWebButton);
            break;
        }
        CommonSteps.switchToMainApp();
    }

    @And("^User verifies that \"([^\"]*)\" language selected in player language settings$")
    public void userVerifiesThatLanguageSelectedInPlayerLanguageSettings(String language) {
        PlayerBlock playerBlock = new PlayerBlock();
        Assertions.elementIsSelected(playerBlock.getLanguageSelectBlock().getWebButtonByLanguage(language));
        CommonSteps.switchToMainApp();
    }

    @When("^User taps on \"(Settings)\" in player$")
    public void userTapsOnInPlayer(String element) {
        CommonSteps.switchToFrameIfNeeded();
        PlayerBlock playerBlock = new PlayerBlock();
        switch (element) {
        case "Settings":
            playerBlock.languageSettingsWebButton.click();
            break;
        }
        CommonSteps.switchToMainApp();
    }

    @And("^User selects \"([^\"]*)\" language in player$")
    public void userSelectsLanguageInPlayer(String language) {
        PlayerBlock playerBlock = new PlayerBlock();
        playerBlock.getLanguageSelectBlock().getNativeButtonByLanguage(language).click();
    }

    @And("^Advertisement iframe is \"(not displayed|displayed)\" at the bottom of player$")
    public void advertisementIsAtTheBottomOfPlayer(String displayed) {
        boolean isShouldBeDisplayed = "displayed".equals(displayed) ? true : false;
        CommonSteps.switchToFrameIfNeeded();
        PlayerBlock playerBlock = new PlayerBlock();
        if (isShouldBeDisplayed) {
            Assertions.elementSizeIsNotZero(playerBlock.advertisementWebBlock);
        } else {
            Assertions.elementIsNOTVisible(playerBlock.advertisementWebBlock);
        }
        CommonSteps.switchToMainApp();
        CommonSteps.screenshot();
    }

    @Then("^User verifies playback is started$")
    public void userVerifiesPlaybackIsStarted() {
        PlayerBlock playerBlock = new PlayerBlock();
        CommonSteps.switchToFrameIfNeeded();
        Waiters.waitDisappearanceOf(Integer.parseInt(ProjectPropertiesUtils.getInstance().getDefaultTimeout()),
                playerBlock.loadingWebButton);
        Assertions.elementIsVisible(playerBlock.pauseWebButton);
        Assertions.elementTextIsNotZero(playerBlock.timerWebLabel);
        CommonSteps.switchToMainApp();
    }

    @Then("^User verifies \"(Powered By)\" label is not displayed in player$")
    public void userVerifiesLabelIsNotDisplayedInPlayer(String label) {
        CommonSteps.switchToFrameIfNeeded();
        PlayerBlock playerBlock = new PlayerBlock();
        switch (label) {
        case "Powered By":
            Assertions.elementIsNOTVisible(playerBlock.poweredByWebLabel);
            Assertions.elementIsNOTVisible(playerBlock.poweredByWhomWebLabel);
            break;
        }
    }
}
