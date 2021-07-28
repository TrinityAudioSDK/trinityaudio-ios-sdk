@DemoApp @MultiPlayersUsage
Feature: Trinity Demo App tests for Multi Players Usage functionality

  @Test_multi_1
  Scenario: Multiple web-players embedding check
    Given Open app on Main page
    When User navigates to "Multiple players usage" page
    Then User see "5" player instances are displayed

