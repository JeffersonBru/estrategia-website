@Consulta
Feature: Consultas por professor e materia

  Background: Usuario navegando atraves da home
    Given Desejo acessar a url "https://www.estrategiaconcursos.com.br/"
    Then Devo ver as opcoes no navbar

  Scenario Outline: Validar por "<validacao>"
    When Eu aciono a opcao de consulta por "<tipoConsulta>"
    And procuro pelo nome "<nomeConsulta>"
    Then valido "<validacao>"
    Examples:
    | tipoConsulta | nomeConsulta   | validacao                                |
    | professor	   | Ena Loiola     | os valores dos cursos com parcelas ou nao|
    | professor    | Ena Loiola     | a quantidade de cursos apresentados      |
    | materia      | Acessibilidade | os valores dos cursos com parcelas ou nao|
