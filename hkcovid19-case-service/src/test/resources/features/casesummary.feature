Feature: the case summary can be retrieved
  Scenario: client makes call to POST /hkcovid19casessummary/getAll
    When the client calls /hkcovid19casessummary/getAll
    Then the client receives status code of 200
    And the client receives a valid CaseSummay JSON