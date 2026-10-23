@PIM
Feature: Employee Search and Filters

  Background:
    Given que el usuario abre el navegador y navega a "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login"
    And que el usuario inicia sesión en OrangeHRM con el usuario "Admin" y la contraseña "admin123"
    And que el usuario ingresa al módulo PIM

  @TC-001
  Scenario Outline: Search employee by name
    When el usuario ingresa "<nombreEmpleado>" en el campo Nombre del empleado de PIM
    And hace clic en Buscar
    Then se deben mostrar los resultados de la búsqueda
    And cada resultado debe contener "<expectedName>"

    Examples:
      | nombreEmpleado | expectedName |
      | Rick           | Sanchez      |

  @TC-002
  Scenario Outline: Search employee by employee id
    When the user enters employee id "<employeeId>"
    And  clicks Search
    Then the system should return "<expectedRecords>" records
    Examples:
      | employeeId | expectedRecords |
      | 0001       | 1               |
      | 999999     | 0               |

  @TC-003
  Scenario Outline: Search employees by employment status
    When the user selects employment status "<status>"
    And clicks Search2
    Then every result should have employment status "<status>"
    Examples:
      | status                |
      | Freelance             |
      | Full-Time Contract    |
      | Full-Time Permanent   |
      | Full-Time Probation   |
      | Part-Time Contract    |

  @TC-004
  Scenario Outline: Search employees using include filter
    When the user selects include option "<includeOption>"
    And clicks Search
    Then results should belong to "<includeOption>"
    Examples:
      | includeOption              |
      | Current Employees Only     |
      | Current and Past Employees |
      | Past Employees Only        |


    @TC-005
    Scenario Outline: Search employees using all available PIM filters
      When the user enters employee name "<employeeName>"
      And the user enters employee id "<employeeId>"
      And the user selects employment status "<status>"
      And the user selects include option "<includeOption>"
      And the user selects job title "<jobTitle>"
      And the user selects sub unit "<subUnit>"
      And the user selects supervisor "<supervisor>"
      And clicks Search
      Then every result should match all selected criteria
      Examples:
        | employeeName | employeeId | status                | includeOption              | jobTitle                 | subUnit      | supervisor     |
        | Amelia      | 01715      | Full-Time Permanent   | Current Employees Only     | Account Assistant        | Administration | John Smith     |
        | James       | 0002      | Full-Time Contract    | Current and Past Employees | Automation Engineer L000 | Engineering  | Peter Anderson |
        | Linda       | 0003      | Part-Time Contract    | Past Employees Only        | Automation Tester        | Development  | Linda Brown    |