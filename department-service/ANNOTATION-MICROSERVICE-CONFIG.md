# Config -  WebClientConfig

Esse script representa uma configuração de um cliente Web (usando o WebClient) no contexto de uma aplicação Spring Boot, especialmente por está trabalhando com Spring Cloud, pois envolve a utilização de LoadBalancedExchangeFilterFunction e HttpServiceProxyFactory. 

```java
package com.microservice.departmentservice.config;

import com.microservice.departmentservice.client.EmployeeClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.reactive.LoadBalancedExchangeFilterFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class WebClientConfig {

    @Autowired
    private LoadBalancedExchangeFilterFunction filterFunction;

    @Bean
    public WebClient employeeWebClient(){
        return WebClient.builder()
                .baseUrl("http://employee-service")
                .filter(filterFunction)
                .build();
    }

    @Bean
    public EmployeeClient employeeClient(){
        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory
                .builder(WebClientAdapter.forClient(employeeWebClient()))
                .build();

        return  httpServiceProxyFactory.createClient(EmployeeClient.class);
    }
}
```

Explicando cada parte:

1. **`@Configuration`:** Indica que a classe é uma classe de configuração Spring, responsável por definir beans e configurações específicas do aplicativo.

2. **`@Autowired private LoadBalancedExchangeFilterFunction filterFunction;`:** Essa anotação é usada para injetar uma instância de `LoadBalancedExchangeFilterFunction`. É para a configuração de balanceamento de carga no aplicativo, que utiliza uma solução de balanceamento de carga do Spring Cloud.

3. **`@Bean public WebClient employeeWebClient() {...}`:**
    - `@Bean`: Indica que um método dentro da classe irá produzir um bean gerenciado pelo Spring.
    - `WebClient.builder()`: Inicia a construção de uma instância do WebClient, que é uma classe fornecida pelo Spring para fazer chamadas HTTP de forma reativa.
    - `.baseUrl("http://employee-service")`: Define a URL base para as chamadas HTTP feitas por esse WebClient. No caso, as chamadas serão feitas para o serviço chamado "employee-service". que é um host registrado no serviço de descoberta (por exemplo, Eureka).
    - `.filter(filterFunction)`: Adiciona um filtro ao WebClient. Neste caso, o filtro de balanceamento de carga (`LoadBalancedExchangeFilterFunction`) está sendo adicionado. Isso significa que o WebClient está ciente do balanceamento de carga configurado no aplicativo.

4. **`@Bean public EmployeeClient employeeClient() {...}`:**
    - Cria um bean do tipo `EmployeeClient`, que é uma interface Feign usada para fazer chamadas ao serviço "employee-service".
    - `HttpServiceProxyFactory`: É uma fábrica para criar proxies para interfaces baseadas em HTTP. Aqui, ela está sendo usada para criar um proxy para a interface `EmployeeClient`.
    - `.builder(WebClientAdapter.forClient(employeeWebClient()))`: Configura a fábrica para usar o WebClient criado anteriormente como cliente HTTP subjacente para o Feign.
    - `.build()`: Constrói a fábrica configurada.
    - `httpServiceProxyFactory.createClient(EmployeeClient.class)`: Cria e retorna uma instância de `EmployeeClient` usando o proxy configurado.

Esse código, em resumo, configura um WebClient e um cliente Feign (`EmployeeClient`) com suporte a balanceamento de carga para fazer chamadas ao serviço "employee-service". Essa abordagem é utilizada para uma arquitetura baseada em microservices, onde há serviços independentes se comunicando através de chamadas HTTP.