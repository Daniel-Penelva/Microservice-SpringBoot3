# Anotação `@EnableDiscoveryClient`

A anotação `@EnableDiscoveryClient` é uma anotação específica do Spring Cloud e é usada para sinalizar a um aplicativo Spring Boot que ele deve se registrar automaticamente em um serviço de descoberta, como o Eureka, Consul ou Zookeeper. Esses serviços de descoberta são fundamentais em arquiteturas de microserviços, pois permitem que os serviços se descubram dinamicamente em um ambiente distribuído.

Explicação de como essa anotação é usada:

1. **Adição da Anotação:**
   Normalmente adiciona a anotação `@EnableDiscoveryClient` em uma classe de configuração do Spring Boot ou na classe principal da aplicação, sinalizando que a aplicação deve participar do serviço de descoberta.

   Exemplo:

   ```java
   import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
   import org.springframework.boot.SpringApplication;
   import org.springframework.boot.autoconfigure.SpringBootApplication;

   @SpringBootApplication
   @EnableDiscoveryClient
   public class MyMicroserviceApplication {
       public static void main(String[] args) {
           SpringApplication.run(MyMicroserviceApplication.class, args);
       }
   }
   ```

2. **Registro Automático:**
   Quando a aplicação é iniciada e a anotação `@EnableDiscoveryClient` está presente, o aplicativo se registra automaticamente no serviço de descoberta configurado no ambiente (como Eureka). Isso significa que outras instâncias do mesmo serviço podem descobrir dinamicamente essa instância registrada.

3. **Configuração Adicional:**
   Além da anotação, é necessário configurar as propriedades relacionadas ao serviço de descoberta no arquivo de configuração da aplicação. Por exemplo, no caso do Eureka, você precisará configurar o nome da aplicação e as informações de conexão com o servidor Eureka.

   Exemplo (application.yml):

   ```yaml
   spring:
     application:
       name: my-microservice
   eureka:
     client:
       serviceUrl:
         defaultZone: http://eureka-server:8761/eureka/
   ```

   Neste exemplo, `my-microservice` é o nome da aplicação que será registrado no serviço de descoberta (Eureka, neste caso).

Em resumo, a anotação `@EnableDiscoveryClient` é usada para habilitar a descoberta automática de serviços em uma arquitetura de microserviços, permitindo que o aplicativo se registre e seja descoberto dinamicamente em um ambiente distribuído.

# Configuração `application.yml`

O arquivo de configuração YAML usado para configurar um aplicativo Spring Boot para um serviço de departamento (department-service) que se registra em um servidor Eureka. 

Analisando cada parte do script:

1. **Configuração do Servidor embutido do Spring Boot:**
   ```yaml
   server:
     port: 8081
   ```
   Aqui, está sendo configurada a porta em que o servidor embutido do Spring Boot deve escutar. O aplicativo será acessível através da porta 8081.

2. **Configuração da Aplicação Spring Boot:**
   ```yaml
   spring:
     application:
       name: department-service
   ```
   Define o nome da aplicação Spring Boot como "department-service". Este nome é usado para identificar exclusivamente a aplicação no contexto do Eureka, que é o serviço de descoberta.

3. **Configuração do Cliente Eureka:**
   ```yaml
   eureka:
     client:
       serviceUrl:
         defaultZone: http://localhost:8761/eureka/
   ```
   Configura o cliente Eureka para se registrar no servidor Eureka. Aqui estão os detalhes:
   - `eureka.client`: Configurações relacionadas ao cliente Eureka.
   - `serviceUrl.defaultZone`: Especifica a URL do servidor Eureka. Neste caso, está configurado para o servidor Eureka na mesma máquina (`localhost`) e na porta padrão (`8761`).

Resumindo, este script configura um aplicativo Spring Boot chamado "department-service" para escutar na porta 8081 e se registrar no servidor Eureka localizado em http://localhost:8761/eureka/. Isso é útil em ambientes de microserviços, onde o Eureka fornece um serviço de descoberta para facilitar a localização e comunicação entre diferentes serviços.