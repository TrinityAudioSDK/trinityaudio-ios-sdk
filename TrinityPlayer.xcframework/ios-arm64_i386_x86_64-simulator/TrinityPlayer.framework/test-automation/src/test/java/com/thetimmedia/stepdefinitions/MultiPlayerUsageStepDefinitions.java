package com.thetimmedia.stepdefinitions;

import com.thetimmedia.pages.MultiPlayerUsagePage;
import com.thetimmedia.utils.Assertions;
import com.thetimmedia.utils.CommonSteps;
import com.thetimmedia.utils.Waiters;
import cucumber.api.java.en.Then;

public class MultiPlayerUsageStepDefinitions {

    @Then("^User see \"([^\"]*)\" player instances are displayed$")
    public void userSeePlayerInstancesAreDisplayed(Integer count) {
        MultiPlayerUsagePage multiPlayerUsagePage = new MultiPlayerUsagePage();
        Assertions.listShouldHaveSize("Player instances", multiPlayerUsagePage.listOfPlayerInstances, count);
        CommonSteps.screenshot();
    }
}
