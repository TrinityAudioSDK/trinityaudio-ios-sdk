package com.thetimmedia.utils;

import com.thetimmedia.driver.Driver;
import com.thetimmedia.fielddecorator.CustomAbstractElement;
import com.thetimmedia.types.Section;
import io.qameta.allure.Step;
import org.hamcrest.Matchers;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Class with assert collection to verify different conditions native iOS elements and WebView elements
 */
public class Assertions {

    @Step("Verify {arg0.name} is visible")
    public static void elementIsVisible(CustomAbstractElement element) {
        if (!element.isDisplayed()) {
            throw new AssertionError(element.getName() + " is not displayed");
        }
    }

    @Step("Verify {arg0.name} is NOT visible")
    public static void elementIsNOTVisible(CustomAbstractElement element) {
        Driver.setImplicitlyWait(0);
        if (element.isDisplayed()) {
            throw new AssertionError(element.getName() + " is displayed, but shouldn't");
        }
        Driver.setDefaultImplicitlyWait();
    }

    @Step("Verify {arg0.name} value is not zero")
    public static void elementTextIsNotZero(CustomAbstractElement element) {
        String value = element.getText();
        if (element.getWrappedElement() == null || "0".equals(value) || "0:00".equals(value)) {
            throw new AssertionError("Attribute 'value' for " + element.getName() + " is not zero. Was: " + value);
        }
    }

    @Step("Verify {arg0.name} size is not zero")
    public static void elementSizeIsNotZero(CustomAbstractElement element) {
        String value = element.getText();
        if (element.getWrappedElement().getSize().getHeight() == 0
                || element.getWrappedElement().getSize().getWidth() == 0) {
            throw new AssertionError("Expected element size is not zero, but was:\n"
                    + "Height: " + element.getWrappedElement().getSize().getHeight() + " is not zero. Was: " + value);
        }
    }

    @Step("Check text in '{arg0.name}' equals to '{arg1}'")
    public static void elementTextEqualsTo(CustomAbstractElement element, String expectedText) {
        assertThat("Element text does not equals to '" + expectedText + "' was: '" + element.getText() + "'",
                element.getText(), Matchers.equalTo(expectedText));
    }

    @Step("Check text in '{arg0.name}' contains '{arg1}'")
    public static void elementContainsText(CustomAbstractElement element, String expectedText) {
        assertThat("Element does not contains expected text '" + expectedText + "', was: '" + element.getText() + "'",
                element.getText(), containsString(expectedText));
    }

    @Step("Check text in '{arg0.name}' NOT contains '{arg1}'")
    public static void elementNOTContainsText(CustomAbstractElement element, String notExpectedText) {
        assertThat(
                "Element does contains NOT expected text '" + notExpectedText + "', was: '" + element.getText() + "'",
                element.getText(), not(containsString(notExpectedText)));
    }

    @Step("Check the '{arg0}' field is equal '{arg2}'")
    public static void expectedStringsAreEqual(String fieldName, String actual, String expected) {
        assertThat("String in field '" + fieldName + "' should be equals to '" + expected + "' but displayed '" + actual
                + "'", expected.equals(actual));
    }

    @Step("Check the '{arg0}' field is NOT equal '{arg2}'")
    public static void expectedStringsAreNOTEqual(String fieldName, String actual, String notExpected) {
        assertThat("String in field '" + fieldName + "' should be NOT equals to '" + notExpected + "' but displayed '"
                + actual + "'", !notExpected.equals(actual));
    }

    @Step("Check the '{arg0}' list has size '{size}'")
    public static void listShouldHaveSize(String listName, List<Section> listOfElements, int size) {
        assertThat("'" + listName + "' list size is not " + size, listOfElements, hasSize(size));
    }

    @Step("Check the '{arg0}' list contains the '{arg1}' list")
    public static void listShouldContainAnotherList(String oneListName, String anotherListName,
            List<String> listOfString, List<String> anotherListOfString) {
        assertThat("'" + oneListName + "' list doesn't contain '" + anotherListName + "' list\n" + "", listOfString,
                containsInAnyOrder(anotherListOfString.toArray(new String[anotherListOfString.size()])));
    }

    @Step("Check at least ONE element from '{arg0}' list should contain '{arg3}' text")
    public static void atLeastOneElementFromListShouldContainText(String listName, List<String> listOfString,
            String expectedText) {
        assertThat("No One Element From '" + listName + "' list contains expected value", listOfString,
                hasItem(containsString(expectedText)));
    }

    @Step("Check the '{arg0.name}' field is selected")
    public static void elementIsSelected(CustomAbstractElement element) {
        assertThat(element.getName() + " is NOT selected", element.getAttribute("class").contains("active"));
    }
}
