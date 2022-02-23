@comaprePrice
Feature: Get the lower price of an iPhone
  Description: the purpose of this feature is to get the lower price of an iPhone between to website

  @getTheLowerIphonePrice
  Scenario: I want to get the lower price of an iphone in two different Website amazone and flipkart
    Given I navigate to myTargetUrl "amazone"
    When I search my iPhone target "iPhone"
    And I select my target iphone in the list
    And I get the price of the selected iPhone
    Given I navigate to myTargetUrl "flipkart"
    When I search my iPhone target "iPhone"
    And I select my target iphone in the list
    And I get the price of the selected iPhone
    Then I compare and deternime the lowest website for my phone target and i print the result on the console
