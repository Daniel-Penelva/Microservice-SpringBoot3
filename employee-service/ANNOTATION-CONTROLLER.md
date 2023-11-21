# Controller - EmployeeController

Este script é um controlador (controller) Spring Boot para uma API REST que lida com operações relacionadas a funcionários (`Employee`).

```java
@RestController
@RequestMapping("api/employee")
public class EmployeeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    // localhost:8081/api/employee/create
    @PostMapping("/create")
    public Employee add(@RequestBody Employee employee){
        LOGGER.info("Employee add: {}", employee);
        return employeeRepository.addEmployee(employee);
    }

    // localhost:8081/api/employee/all
    @GetMapping("/all")
    public List<Employee> findAll(){
        LOGGER.info("Employee find");
        return employeeRepository.findAll();
    }

    // localhost:8081/api/employee/search/{id}
    @GetMapping("/search/{id}")
    public Employee findById(@PathVariable Long id){
        LOGGER.info("Employee find: id={}", id);
        return employeeRepository.findById(id);
    }

    // localhost:8081/api/employee/department/{departmentId}
    @GetMapping("/department/{departmentId}")
    public List<Employee> findByDepartment(@PathVariable("departmentId") Long departmentId) {
        LOGGER.info("Employee find: departmentId={}", departmentId);
        return employeeRepository.findByDepartment(departmentId);
    }
}
```

Explicando cada parte do código:

1. **`@RestController` e `@RequestMapping("api/employee")`:**
    - `@RestController` é uma anotação do Spring que combina `@Controller` e `@ResponseBody`. Indica que a classe é um controlador REST que retorna dados diretamente no corpo da resposta HTTP.
    - `@RequestMapping("api/employee")` especifica o mapeamento base para todas as solicitações relacionadas a funcionários. Portanto, todas as URLs neste controlador começarão com "/api/employee".

2. **`private static final Logger LOGGER`:**
    - Cria um logger estático para a classe usando o SLF4J (Simple Logging Facade for Java).

3. **`@Autowired private EmployeeRepository employeeRepository;`:**
    - `@Autowired` é uma anotação do Spring que injeta automaticamente uma instância de `EmployeeRepository` no controlador.
    - `EmployeeRepository` é provavelmente uma classe que gerencia operações no banco de dados ou na fonte de dados relacionada aos funcionários.

4. **`@PostMapping("/create")`:**
    - Mapeia solicitações HTTP POST para o caminho "/api/employee/create".
    - `@RequestBody Employee employee` indica que o corpo da solicitação HTTP deve ser convertido em um objeto `Employee`.
    - O método `add` adiciona o funcionário usando o `EmployeeRepository` e retorna o funcionário adicionado.

5. **`@GetMapping("/all")`:**
    - Mapeia solicitações HTTP GET para o caminho "/api/employee/all".
    - O método `findAll` retorna uma lista de todos os funcionários usando o `EmployeeRepository`.

6. **`@GetMapping("/search/{id}")`:**
    - Mapeia solicitações HTTP GET para o caminho "/api/employee/search/{id}".
    - `@PathVariable Long id` extrai o valor da variável de caminho `{id}` e o utiliza para buscar um funcionário por ID usando o `EmployeeRepository`.

7. **`@GetMapping("/department/{departmentId}")`:**
    - Mapeia solicitações HTTP GET para o caminho "/api/employee/department/{departmentId}".
    - `@PathVariable("departmentId") Long departmentId` extrai o valor da variável de caminho `{departmentId}` e o utiliza para buscar uma lista de funcionários por departamento usando o `EmployeeRepository`.

Essencialmente, esse controlador fornece uma API REST para realizar operações CRUD (Create, Read, Update, Delete) em entidades `Employee`. Os métodos do controlador correspondem a diferentes endpoints da API, manipulando operações específicas. O logger (`LOGGER`) é utilizado para registrar informações sobre as operações realizadas.