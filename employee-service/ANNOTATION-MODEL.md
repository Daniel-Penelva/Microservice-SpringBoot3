# Record

O `record` é uma nova funcionalidade introduzida no Java 14, destinada a simplificar a criação de classes que são essencialmente contêineres de dados imutáveis.

## Características Adicionais de `record`:

1. **Métodos de Acesso (Accessors):**
    - Além dos métodos padrão como `toString()`, `equals()`, e `hashCode()`, um `record` também gera automaticamente métodos de acesso (accessors) para cada campo.
    - Por exemplo, se tivermos um campo chamado `name`, automaticamente teremos um método de acesso chamado `name()` que retorna o valor desse campo.

2. **Métodos Personalizados:**
    - Além dos métodos gerados automaticamente, você pode incluir métodos personalizados dentro de um `record`.
    - Esses métodos personalizados podem fornecer funcionalidades específicas à classe.

3. **Desconstrução (Destructuring):**
    - Os `records` suportam a desconstrução (destructuring), o que permite que extraia os valores dos campos diretamente, como em atribuições ou expressões.

## Exemplo Adicional:

Exemplo adicional usando um `record` chamado `Person`:

```java
public record Person(String firstName, String lastName, int age) {
    // Método personalizado que retorna o nome completo
    public String fullName() {
        return firstName + " " + lastName;
    }

    // Método personalizado para verificar se a pessoa é adulta
    public boolean isAdult() {
        return age >= 18;
    }
}

public class RecordExample {
    public static void main(String[] args) {
        // Criando uma instância de Person usando o construtor gerado automaticamente
        Person person = new Person("Alice", "Smith", 25);

        // Aproveitando o método toString() gerado automaticamente
        System.out.println(person);

        // Usando métodos de acesso gerados automaticamente
        System.out.println("First Name: " + person.firstName());
        System.out.println("Last Name: " + person.lastName());
        System.out.println("Age: " + person.age());

        // Usando métodos personalizados
        System.out.println("Full Name: " + person.fullName());
        System.out.println("Is Adult? " + person.isAdult());

        // Desconstrução (destructuring)
        String firstName = person.firstName();
        String lastName = person.lastName();
        int age = person.age();

        System.out.println("Desconstrução: " + firstName + " " + lastName + ", Age: " + age);
    }
}
```

Neste exemplo, o `record` `Person` tem métodos personalizados `fullName()` e `isAdult()`. Além disso, é demonstrada a desconstrução, onde os valores dos campos são extraídos diretamente. Essa é uma característica poderosa que simplifica a manipulação de dados em muitos casos.

# Model - Employee

Explicação detalhada do código e uma introdução ao conceito de `record`:

1. **Record `Employee`:**

   ```java
   public record Employee(Long id, Long departmentId, String name, int age, String position) {
   }
   ```
    - O `record` é uma forma concisa de declarar uma classe que é usada principalmente para armazenar dados imutáveis (modelos de dados).
    - O `record` inclui automaticamente métodos como `toString()`, `equals()`, e `hashCode()` com base nos campos declarados.
    - Os campos declarados no `record` (como `id`, `departmentId`, `name`, `age`, `position`) são automaticamente tornados finais e privados. Eles também são automaticamente incluídos nos métodos mencionados acima.
    - Os `records` são imutáveis por padrão, o que significa que uma vez que um `record` é criado, seus valores não podem ser alterados. Se quiser criar uma instância com valores diferentes, você criaria uma nova instância.

2. **Características do `record`:**
    - **Conciso e Legível:** Os `records` são uma maneira mais concisa e legível de definir classes de dados. Eles eliminam a necessidade de escrever manualmente métodos como `toString()`, `equals()`, e `hashCode()`.
    - **Imutabilidade:** Os campos de um `record` são automaticamente tornados finais e imutáveis.
    - **Métodos Padrão:** Métodos padrão como `toString()`, `equals()`, e `hashCode()` são gerados automaticamente pelo compilador, tornando o código mais limpo.
    - **Destruição Declarativa:** Os `records` permitem uma forma de destruição declarativa, o que significa que você pode extrair automaticamente os valores dos campos sem precisar escrever métodos de acesso explícitos.

3. **Exemplo de Uso do `Employee`:**
4. 
```
   Employee employee = new Employee(1L, 101L, "John Doe", 30, "Software Engineer");
   System.out.println(employee); // Automaticamente usa o toString() gerado
```

   Ao usar um `record`, você pode criar uma instância de `Employee` e imprimi-la, aproveitando automaticamente a implementação gerada do método `toString()`.

Em resumo, os `records` no Java são uma maneira eficiente e concisa de modelar classes de dados imutáveis, economizando tempo e reduzindo a quantidade de código boilerplate necessário para criar classes simples de contêiner de dados.