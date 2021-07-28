package com.thetimmedia.stepdefinitions;

import com.thetimmedia.driver.Driver;
import com.thetimmedia.pages.DemoMainPage;
import com.thetimmedia.pages.MainSDKUsagePage;
import com.thetimmedia.utils.CommonSteps;
import com.thetimmedia.utils.ProjectPropertiesUtils;
import com.thetimmedia.utils.Waiters;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

public class CommonStepDefinitions {

    @Given("^Open app on Main page$")
    public void openApp() {
        Driver.getDriver().resetApp();
    }

    @When("^User navigates to \"(Main SDK Usage|Multiple players usage|Cast player)\" page$")
    public void userNavigatesToPage(String link) {
        DemoMainPage mainPage = new DemoMainPage();
        mainPage.clickOnLink(link);
        Waiters.waitAppearanceOf(Integer.parseInt(ProjectPropertiesUtils.getInstance().getDefaultTimeout()), new MainSDKUsagePage().playerBlock.playStopNativeButton);
        CommonSteps.screenshot();
   }


}
