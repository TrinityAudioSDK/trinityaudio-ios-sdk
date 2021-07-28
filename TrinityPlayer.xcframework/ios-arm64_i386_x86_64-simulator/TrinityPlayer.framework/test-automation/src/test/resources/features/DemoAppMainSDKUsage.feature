@DemoApp @MainSDKUsage
Feature: Trinity Demo App tests for Main SDK Usage functionality

  Background:
    Given Open app on Main page
    And User navigates to "Main SDK Usage" page

  @Test_main_1 @test
  Scenario: The web-player embedding check
    Then User verifies Embedded player is "displayed"
    And User is able to play article

  @Test_main_2 @test
  Scenario: FAB displaying check
    Then User verifies 'FAB' element is "not displayed"
    When User scroll "down" to "hide" player
    Then User verifies 'FAB' element is "displayed"
    When User taps "Play" button on Fab element
    And User scroll "up" to "show" player
    Then User verifies 'FAB' element is "not displayed"
    And User verifies "Pause" button is "displayed" in player

  @Test_main_3 @test
  Scenario: Player components check
    Then User verifies "Play" button is "displayed" in player
    And User verifies "Settings" button is "displayed" in player
    And User verifies "Title" label contains "Listen to this article now" text in player
    And User verifies "Powered By" label contains "Powered by Trinity Audio" text in player
    And User verifies "Timer" label not contains "zero" value in player
    When User taps "Play" button in player
    And User verifies "Pause" button is "displayed" in player
    And User verifies 'Progress' bar is "displayed" in player
    And User verifies "Settings" button is "displayed" in player
    And User verifies "Title" label contains "Playing article" text in player
    And User verifies "Powered By" label contains "Powered by Trinity Audio" text in player
    And User verifies "Timer" label not contains "zero" value in player
    When User taps "Pause" button in player
    Then User verifies "Play" button is "displayed" in player

  @Test_main_4
  Scenario: Player settings: Powered by
    When User switches 'Powered By' toggle to "enabled" in app settings
    And User applies changes
    Then User verifies "Powered By" label contains "Powered by Trinity Audio" text in player
    When User switches 'Powered By' toggle to "disabled" in app settings
    And User applies changes
    Then User verifies "Powered By" label is not displayed in player

  @Test_main_5
  Scenario Outline: Player settings: Language <language>
    Then User verifies "Language" select contains "all languages" in app settings
    When User selects "<lang>" option from "Language" select in app settings
    And User applies changes
    Then User verifies "Title" label contains "<titleText>" text in player
#    And User verifies "Powered By" label contains "Offerto da Trinity Audio" text in player
    And User verifies that "<language>" language selected in player language settings
    Examples:
      | lang | language       | titleText             |
      | it   | Italian        | Ascolta ora           |

  @Test_main_6
  Scenario: Player settings: Select Language that is not in the player language settings
    Then User verifies "Language" select contains "all languages" in app settings
    When User selects "pt" option from "Language" select in app settings
    And User applies changes
    Then User verifies "Title" label contains "Ouça agora" text in player
    And User verifies "Settings" button is "not displayed" in player

  @Test_main_7
  Scenario: Player settings: Voice gender and id
    Then User verifies "Voice gender" select contains "all genders" in app settings
    When User selects "Male" option from "Voice gender" select in app settings
    And User selects "Kevin" option from "Voice ID" select in app settings
#    And User applies changes
#    Then User verifies that "Voice" changes applied in player

  @Test_main_8
  Scenario Outline: Switching language in player - switches voice language only, but not labels
    When User selects "<language>" language in player
    Then User verifies playback is started
    And User verifies "Title" label contains "Playing article" text in player
    And User verifies "Powered By" label contains "Powered by Trinity Audio" text in player
    Examples:
      | language |
      | Spanish  |
      | French   |

  @Test_main_9
  Scenario: Player with/without display Ad
    And User taps "Play" button in player
    And Advertisement iframe is "not displayed" at the bottom of player
    When User taps "Trinity with display Ad" button in app settings
    And User applies changes
    And User taps "Play" button in player
    And Advertisement iframe is "displayed" at the bottom of player