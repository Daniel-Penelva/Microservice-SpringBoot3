# Anotação `@EnableEurekaServer`

A anotação `@EnableEurekaServer` faz parte do ecossistema Spring Cloud e é usada para configurar um aplicativo Spring Boot como um servidor Eureka. O Eureka é um serviço de descoberta que permite que os microserviços se registrem e descubram uns aos outros em um ambiente distribuído.

Quando anota uma classe de configuração ou uma classe principal do Spring Boot com `@EnableEurekaServer` está indicando ao Spring que este aplicativo deve ser configurado como um servidor Eureka. O servidor Eureka é responsável por manter um registro de todos os microserviços registrados e disponíveis em um sistema.

Exemplo simples de como poderia usar essa anotação em uma classe Spring Boot:

```java
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class, args);
    }
}
```

Neste exemplo, a classe `EurekaServerApplication` é anotada com `@SpringBootApplication` para indicar que é uma classe de configuração do Spring Boot. Além disso, a anotação `@EnableEurekaServer` é usada para configurar este aplicativo como um servidor Eureka.

Depois de iniciar este aplicativo, ele funcionará como um servidor Eureka, e outros microserviços podem se registrar nele. Isso facilita a descoberta de serviços em um ambiente distribuído, pois os microserviços podem consultar o servidor Eureka para obter informações sobre os serviços disponíveis e seus locais.

Essa anotação é particularmente útil em arquiteturas de microserviços, onde a descoberta dinâmica de serviços é essencial para facilitar a escalabilidade e a flexibilidade da arquitetura.

# Configuração `application.yml`

O arquivo de configuração YAML é usado para configurar um servidor Eureka, que é um componente do Spring Cloud usado para a descoberta de serviços em ambientes de microserviços. 
Analisando as principais configurações presentes nesse script:

```yaml
server:
  port: 8761
```
Essa seção configura as propriedades do servidor embutido do Spring Boot. Neste caso, o servidor está configurado para escutar na porta 8761. O Eureka Server geralmente usa esta porta como padrão.

```yaml
spring:
  application:
    name: service-registry
```
Essa parte define a configuração da aplicação Spring Boot. O nome da aplicação é definido como "service-registry". Este é o nome pelo qual a aplicação será registrada no Eureka Server.

```yaml
eureka:
  instance:
    hostname: localhost
  client:
    register-with-eureka: false
    fetch-registry: false
    service-url:
      defaultZone: http://${eureka.instance.hostname}:${server.port}/eureka/
```
Aqui está a configuração específica do Eureka:

- `eureka.instance.hostname: localhost`: Define o hostname da instância Eureka como "localhost". Isso é importante para que outras instâncias possam se comunicar com este servidor Eureka.

- `eureka.client.register-with-eureka: false`: Esta propriedade indica se este servidor Eureka deve se registrar com outro servidor Eureka. Neste caso, está configurado como "false", indicando que este servidor Eureka não deve se registrar em outro servidor Eureka.

- `eureka.client.fetch-registry: false`: Indica se este servidor Eureka deve buscar informações do registro de outros servidores Eureka. Neste caso, está configurado como "false", o que significa que este servidor Eureka não irá buscar informações de outros servidores.

- `eureka.client.service-url.defaultZone`: Define a URL do servidor Eureka para a instância atual. O padrão é definido como "http://${eureka.instance.hostname}:${server.port}/eureka/". Isso especifica onde outros serviços Eureka podem encontrar este servidor. O `${eureka.instance.hostname}` é substituído pelo valor de `eureka.instance.hostname` definido anteriormente, e `${server.port}` é substituído pelo valor de `server.port`.

Resumindo, esse script configura um servidor Eureka que escuta na porta 8761, com o nome da aplicação "service-registry", e é configurado para não se registrar ou buscar informações de outros servidores Eureka.