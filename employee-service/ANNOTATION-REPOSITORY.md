# Repository - EmployeeRepository

O script apresenta uma classe chamada `EmployeeRepository`, que parece ser uma implementação simples de um repositório para gerenciar objetos do tipo `Employee`. Vamos analisar cada método e sua função:

```java
@Repository
public class EmployeeRepository {

    private List<Employee> employees = new ArrayList<>();

    // Método para adicionar um funcionário à lista
    public Employee addEmployee(Employee employee){
        employees.add(employee);
        return  employee;
    }

    // Método para encontrar um funcionário por ID
    public Employee findById(Long id){
        return employees.stream()
                .filter(employee -> employee.id().equals(id))
                .findFirst()
                .orElseThrow();
    }

    // Método para obter todos os funcionários
    public List<Employee> findAll(){
        return employees;
    }

    // Método para encontrar funcionários por departamento
    public List<Employee> findByDepartment(Long departamentId){
        return employees.stream()
                .filter(d -> d.departmentId().equals(departamentId))
                .toList();
    }
}
```

Explicação detalhada:

1. **`@Repository` Annotation:**
    - `@Repository` é uma anotação do Spring que indica que a classe é um repositório, geralmente usado para acessar dados. Essa anotação é usada para habilitar a tradução de exceções específicas de persistência de dados em exceções mais genéricas do Spring.

2. **Lista de Funcionários (`employees`):**
    - A classe mantém uma lista de funcionários, representando a fonte de dados para o repositório. No entanto, vale notar que essa implementação não é persistente e não armazena os dados permanentemente (como em um banco de dados). A lista é apenas uma simulação de um repositório em memória.

3. **`addEmployee` Método:**
    - Adiciona um funcionário à lista de funcionários e retorna o próprio funcionário.

4. **`findById` Método:**
    - Procura um funcionário na lista pelo ID e retorna o primeiro encontrado.
    - Se nenhum funcionário for encontrado, lança uma exceção (o método `orElseThrow` é usado para lançar uma exceção padrão quando nenhum elemento é encontrado).

5. **`findAll` Método:**
    - Retorna toda a lista de funcionários.

6. **`findByDepartment` Método:**
    - Retorna uma lista de funcionários filtrados por departamento, com base no ID do departamento fornecido.

Este código representa um repositório simples em memória para a entidade `Employee`. Vale ressaltar que essa aplicação não é aplicada no mundo real, um repositório seria integrado a um banco de dados ou outro sistema de armazenamento persistente para fornecer operações de leitura e gravação persistentes.

# Funcionalidade dos métodos `stream()` e `filter()`

A funcionalidade dos métodos `stream()` e `filter()` que estão sendo usados nos métodos `findById` e `findByDepartment` da classe `EmployeeRepository`.

1. **`stream()` Método:**
    - O método `stream()` é um recurso introduzido no Java 8 que permite a criação de uma sequência de elementos que pode ser processada em operações de fluxo (stream operations).
    - Uma stream é uma sequência de elementos que suporta várias operações, como filtragem, mapeamento, redução, etc.
    - O método `stream()` é frequentemente usado para converter uma coleção (como uma lista) em uma sequência de elementos, permitindo operações mais flexíveis e expressivas.

2. **`filter()` Método:**
    - O método `filter()` é uma operação de fluxo que é usada para filtrar elementos com base em uma condição específica.
    - Ele aceita um predicado (uma expressão lambda ou uma referência de método) que é aplicado a cada elemento da stream.
    - Elementos que atendem à condição do predicado são incluídos na stream resultante, enquanto os que não atendem são excluídos.

Vamos ver como esses métodos são usados nos métodos `findById` e `findByDepartment`:

## Método `findById`:

```java
public Employee findById(Long id){
    return employees.stream()
            .filter(employee -> employee.id().equals(id))
            .findFirst()
            .orElseThrow();
}
```

Neste método:
- `employees.stream()`: Converte a lista `employees` em uma stream.
- `.filter(employee -> employee.id().equals(id))`: Filtra os elementos da stream para incluir apenas aqueles cujo ID é igual ao ID fornecido como argumento.
- `.findFirst()`: Retorna o primeiro elemento da stream resultante, ou um `Optional` vazio se nenhum elemento for encontrado.
- `.orElseThrow()`: Lança uma exceção se o `Optional` estiver vazio, indicando que nenhum funcionário foi encontrado com o ID fornecido.

## Método `findByDepartment`:

```java
public List<Employee> findByDepartment(Long departamentId){
    return employees.stream()
            .filter(d -> d.departmentId().equals(departamentId))
            .toList();
}
```

Neste método:
- `employees.stream()`: Converte a lista `employees` em uma stream.
- `.filter(d -> d.departmentId().equals(departamentId))`: Filtra os elementos da stream para incluir apenas aqueles cujo ID do departamento é igual ao ID do departamento fornecido como argumento.
- `.toList()`: Coleta os elementos filtrados em uma lista e a retorna.

Em resumo, os métodos `stream()` e `filter()` são poderosos recursos introduzidos no Java 8 para trabalhar com coleções de forma mais flexível e expressiva, permitindo operações de filtragem e transformação de maneira mais concisa. Esses métodos são comumente usados em conjunto com outras operações de stream para realizar tarefas como busca, filtragem, mapeamento e redução de dados.
