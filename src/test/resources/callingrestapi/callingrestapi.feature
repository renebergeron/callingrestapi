Feature: Call a REST API
    First eploration test


    Scenario: We first try to get a result calling rest api
    Given restapiurl is "http://localhost:8080/cxf/rest/product/offer/GetSubscribedOffers/04519469"
    When calling rest api
    Then valid the json format
    And tester les donnees
