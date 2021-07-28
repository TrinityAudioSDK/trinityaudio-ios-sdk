package com.thetimmedia.stepdefinitions;

import com.thetimmedia.pages.MainSDKUsagePage;
import com.thetimmedia.pages.blocks.PlayerBlock;
import com.thetimmedia.pages.blocks.PlayerSettingsBlock;
import com.thetimmedia.utils.Assertions;
import com.thetimmedia.utils.CommonSteps;
import com.thetimmedia.utils.ProjectPropertiesUtils;
import com.thetimmedia.utils.Waiters;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.Arrays;
import java.util.List;

public class SettingsStepDefinitions {

    @Then("^User verifies \"(Language|Voice gender)\" select contains \"(all languages|all genders|.*)\" in app settings$")
    public void userVerifiesSelectContainsAllInAppSettings(String select, String expected) {
        MainSDKUsagePage mainSDKUsagePage = new MainSDKUsagePage();
        switch (select) {
        case "Language":
            List<String> actualLangsList = mainSDKUsagePage.settingsBlock.languageSelect.getAllOptionsAsString();
            if (expected.toLowerCase().contains("languages")) {
                List<String> expectedLangsList = Arrays.asList("en", "es", "it", "fr", "de", "pt");
                Assertions.listShouldContainAnotherList("expected Languages list", "actual Languages list",
                        expectedLangsList, actualLangsList);
            } else {
                Assertions
                        .atLeastOneElementFromListShouldContainText("actual Languages list", actualLangsList, expected);
            }
            break;
        case "Voice gender":
            List<String> actualVoiceList = mainSDKUsagePage.settingsBlock.voiceGenderSelect.getAllOptionsAsString();
            if (expected.toLowerCase().contains("genders")) {
                List<String> expectedVoiceList = Arrays.asList("Male", "Female");
                Assertions.listShouldContainAnotherList("expected Voice Gender list", "actual Voice Gender list",
                        expectedVoiceList, actualVoiceList);
            } else {
                Assertions.atLeastOneElementFromListShouldContainText("actual Voice Gender list", actualVoiceList,
                        expected);
            }
            break;
        }
    }

    @When("^User selects \"([^\"]*)\" option from \"(Language|Voice gender|Voice ID)\" select in app settings$")
    public void userSelectsLanguageFromSelectInAppSettings(String value, String select) {
        MainSDKUsagePage mainSDKUsagePage = new MainSDKUsagePage();
        switch (select) {
        case "Language":
            mainSDKUsagePage.settingsBlock.languageSelect.selectByVisibleText(value);
            break;
        case "Voice gender":
            mainSDKUsagePage.settingsBlock.voiceGenderSelect.selectByVisibleText(value);
            break;
        case "Voice ID":
            mainSDKUsagePage.settingsBlock.voiceIdSelect.selectByVisibleText(value);
            break;
        }

    }

    @And("^User applies changes$")
    public void userAppliesChanges() {
        MainSDKUsagePage mainSDKUsagePage = new MainSDKUsagePage();
        mainSDKUsagePage.settingsBlock.applyLink.click();
        Waiters.waitAppearanceOf(Integer.parseInt(ProjectPropertiesUtils.getInstance().getDefaultTimeout()), new PlayerBlock().playStopNativeButton);
        CommonSteps.screenshot();
    }

    @When("^User taps \"(Trinity|Trinity with display Ad)\" button in app settings$")
    public void userTapsButtonInAppSettings(String button) {
        PlayerSettingsBlock playerSettingsBlock = new PlayerSettingsBlock();
        switch (button) {
        case "Trinity":
            playerSettingsBlock.trinityButton.click();
            break;
        case "Trinity with display Ad":
            playerSettingsBlock.trinityWithDisplayAddButton.click();
            break;
        }
    }

    @When("^User switches 'Powered By' toggle to \"(enabled|disabled)\" in app settings$")
    public void userPoweredByToggleInAppSettings(String enable) {
        boolean isEnable = "enabled".equals(enable) ? true : false;
        PlayerSettingsBlock playerSettingsBlock = new PlayerSettingsBlock();
        if (isEnable) {
            playerSettingsBlock.poweredByToggle.enable();
        } else {
            playerSettingsBlock.poweredByToggle.disable();
        }
        CommonSteps.screenshot();
    }
}
