package com.thetimmedia.stepdefinitions;

import com.thetimmedia.pages.MainSDKUsagePage;
import com.thetimmedia.pages.blocks.PlayerBlock;
import com.thetimmedia.utils.Assertions;
import com.thetimmedia.utils.CommonSteps;
import com.thetimmedia.utils.ProjectPropertiesUtils;
import com.thetimmedia.utils.Waiters;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class MainSDKUsageStepDefinitions {

    @Then("^User verifies Embedded player is \"(displayed|not displayed)\"$")
    public void userVerifiesEmbededPlayerIsDisplayed(String displayed) {
        boolean isShouldBeDisplayed = "displayed".equals(displayed) ? true : false;
        MainSDKUsagePage mainSDKUsagePage = new MainSDKUsagePage();
        if (isShouldBeDisplayed) {
            Waiters.waitAppearanceOf(Integer.parseInt(ProjectPropertiesUtils.getInstance().getDefaultTimeout()), mainSDKUsagePage.playerBlock.playerFrame);
            Assertions.elementIsVisible(mainSDKUsagePage.playerBlock.playerFrame);
        } else {
            Assertions.elementIsNOTVisible(mainSDKUsagePage.playerBlock.playerFrame);
        }
        CommonSteps.screenshot();
    }

    @Then("^User verifies 'FAB' element is \"(displayed|not displayed)\"$")
    public void userVerifiesElementIs(String displayed) {
        boolean isShouldBeDisplayed = "displayed".equals(displayed) ? true : false;
        PlayerBlock playerBlock = new PlayerBlock();
        if (isShouldBeDisplayed) {
            Assertions.elementIsVisible(playerBlock.playStopFABNativeButton);
        } else {
            Assertions.elementIsNOTVisible(playerBlock.playStopFABNativeButton);
        }
        CommonSteps.screenshot();
    }

    @When("^User scroll \"(up|down)\" to \"(hide|show)\" player$")
    public void userScrollToPlayer(String direction, String hideShow) {
        MainSDKUsagePage mainSDKUsagePage = new MainSDKUsagePage();
        for (int i = 0; i <= 5; i++) {
            if ("down".equals(direction)) {
                CommonSteps.swipeUp();
            } else {
                CommonSteps.swipeDown();
            }
            if (("hide".equals(hideShow) && !mainSDKUsagePage.playerBlock.playerFrame.isDisplayed(false)) || (
                    "show".equals(hideShow) && mainSDKUsagePage.playerBlock.playerFrame.isDisplayed(false))) {
                break;
            }
            if (i == 5) {
                if ("show".equals(hideShow)) {
                    throw new AssertionError(mainSDKUsagePage.playerBlock.playerFrame + "is not Found");
                } else {
                    throw new AssertionError(mainSDKUsagePage.playerBlock.playerFrame + "is still displayed");
                }
            }
        }
    }
}
