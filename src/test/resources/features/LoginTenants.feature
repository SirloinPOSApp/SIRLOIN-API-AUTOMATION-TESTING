Feature: Testing API Login Tenant

  @CapStone @Positive-Case @Login
  Scenario Outline: POST login tenant with valid email and password
    Given Post login with valid email and password
    When Send request login
    Then Should return status code 200
    And Response body page should be <id>, "<business_name>", "<email>", "<address>", "<phone_number>", "<token>" and "<message>"
    And Validate json schema login
    Examples:
      | id | business_name | email                   | address              | phone_number | token                                                                                                                       | message       |
      | 24 | TES QE TIM    | wirabint.9419@gmail.com | Jln. Kesuksesan No.9 | 081221531590 | eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdXRob3JpemVkIjp0cnVlLCJ1c2VySUQiOjI0fQ.pI_POmwE6aheZHbhJE0nudMNJKl8Ug-30bNzp2r9_3o | login success |

  @CapStone @Negative-Case @Login
  Scenario Outline: Post login tenant with invalid email and valid password
    Given Post login with invalid email and password
    When Send request login
    Then Should return status code <statusCode>
    And Response body page should be <message>
    And Validate json schema login negative case
    Examples:
      | statusCode | message       |
      | 404        | "wrong email" |

  @CapStone @Negative-Case @Login
  Scenario Outline: Post login tenant with empty email and valid password
    Given Post login with empty email and valid password
    When Send request login
    Then Should return status code <statusCode>
    And Response body page should be <message>
    And Validate json schema login negative case
    Examples:
      | statusCode | message       |
      | 404        | "wrong email" |

  @CapStone @Negative-Case @Login
  Scenario Outline: Post login tenant with valid email and invalid password
    Given Post login with valid email and invalid password
    When Send request login
    Then Should return status code <statusCode>
    And Response body page should be <message>
    And Validate json schema login negative case
    Examples:
      | statusCode | message          |
      | 401        | "wrong password" |

  @CapStone @Negative-Case @Login
  Scenario Outline: Post login tenant with empty email and password
    Given Post login with empty email and password
    When Send request login
    Then Should return status code <statusCode>
    And Response body page should be <message>
    And Validate json schema login negative case
    Examples:
      | statusCode | message       |
      | 404        | "wrong email" |

  @CapStone @Negative-Case @Login
  Scenario Outline: Post login tenant with wrong combination email and password
    Given Post login with wrong combination email and password
    When Send request login
    Then Should return status code <statusCode>
    And Response body page should be <message>
    And Validate json schema login negative case
    Examples:
      | statusCode | message       |
      | 404        | "wrong email" |

  @CapStone @Negative-Case @Login
  Scenario Outline: Post login tenant with unregistered email
    Given Post login with unregistered email
    When Send request login
    Then Should return status code <statusCode>
    And Response body page should be <message>
    And Validate json schema login negative case
    Examples:
      | statusCode | message       |
      | 404        | "wrong email" |

  @CapStone @Negative-Case @Login
  Scenario Outline: Post login tenant with invalid path
    Given Post login with invalid path <id>
    When Send request login invalid path
    Then Should return status code <statusCode>
    And Response body page should be <message>
    And Validate json schema login negative case
    Examples:
      | id | statusCode | message     |
      | 1  | 404        | "Not Found" |





