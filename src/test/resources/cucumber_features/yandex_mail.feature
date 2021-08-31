Feature: Yandex mail test

  Background: Yandex login
    Given user navigates to yandex login page
    When enters user credentials and logs in
    Then yandex home page is displayed
    And user navigates to yandex mail page

  @yandexTest
  Scenario Outline: Yandex mail
    Given user is on yandex mail page
    When user performs "<action>" action on email
    Then email is in "<folder>"
    Examples:
      | action | folder |
      | create | draft  |
      | send   | sent   |

