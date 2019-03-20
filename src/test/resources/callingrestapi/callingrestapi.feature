Feature: Call a REST API
    First eploration test


    Scenario: We first try to get a result calling rest api
    Given restapiurl is "http://localhost:8080/cxf/rest/product/offer/GetSubscribedOffers/00000006"
    When calling rest api
    Then server should handle it an return success status
