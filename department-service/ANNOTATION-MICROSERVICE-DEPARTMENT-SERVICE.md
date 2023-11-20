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

# Zipkin

Zipkin é um sistema de rastreamento distribuído. Ajuda a coletar dados de tempo necessários para solucionar problemas de latência em arquiteturas de serviço. Os recursos incluem a coleta e a pesquisa desses dados.

Se você tiver um ID de rastreamento em um arquivo de log, poderá ir diretamente para ele. Caso contrário, você pode consultar com base em atributos como serviço, nome da operação, tags e duração. Alguns dados interessantes serão resumidos para você, como o percentual de tempo gasto em um serviço e se as operações falharam ou não.

A UI do Zipkin também apresenta um diagrama de dependências mostrando quantas solicitações rastreadas passaram por cada aplicativo. Isto pode ser útil para identificar o comportamento agregado, incluindo caminhos de erro ou chamadas para serviços obsoletos.

A aplicação precisa ser “instrumentada” para relatar dados de rastreamento ao Zipkin. Isso geralmente significa configuração de um rastreador ou biblioteca de instrumentação. As formas mais populares de relatar dados ao Zipkin são via http ou Kafka, embora existam muitas outras opções, como Apache ActiveMQ, gRPC e RabbitMQ. Os dados servidos à IU são armazenados na memória ou de forma persistente com um back-end compatível, como Apache Cassandra ou Elasticsearch.

Vou iniciar o Zipkin por via Docker - comando:
docker run -d -p 9411:9411 openzipkin/zipkin

Site - Fonte: https://hub.docker.com/r/openzipkin/zipkin

Acessar o localhost: localhost/9411

## Adicionando as dependências Zipkin (OBSERVABILITY)

Essas dependências publicará todos os logs no localhost 9411

```xml
 <dependencies>
   <!-- Outras dependências-->
   
    <dependency>
			<groupId>io.micrometer</groupId>
			<artifactId>micrometer-tracing-bridge-brave</artifactId>
    </dependency>
    <dependency>
			<groupId>io.zipkin.reporter2</groupId>
			<artifactId>zipkin-reporter-brave</artifactId>
    </dependency>
</dependencies>
```
Essas dependências estão relacionadas ao rastreamento de chamadas em sistemas distribuídos usando o OpenTracing e o Zipkin. 
Analisando cada uma delas:

1. **`io.micrometer:micrometer-tracing-bridge-brave`**:
   - **Propósito:**
      - Esta dependência está relacionada ao Micrometer, que é uma biblioteca de métricas para Java aplicável a sistemas distribuídos. O `micrometer-tracing-bridge-brave` é uma ponte que conecta o Micrometer ao Brave, que é uma biblioteca para rastreamento distribuído.
   - **Funcionalidade:**
      - Ao integrar o Brave com o Micrometer usando essa dependência, você pode coletar métricas e rastreamentos para operações em sistemas distribuídos.
      - O Brave ajuda a rastrear o fluxo de uma solicitação por vários serviços em um ambiente distribuído.

2. **`io.zipkin.reporter2:zipkin-reporter-brave`**:
   - **Propósito:**
      - Esta dependência está relacionada ao Zipkin, que é uma plataforma de rastreamento distribuído. O `zipkin-reporter-brave` é um componente que ajuda a relatar os rastreamentos gerados pelo Brave para um servidor Zipkin.
   - **Funcionalidade:**
      - O Brave, ao ser utilizado com o Zipkin, pode enviar os dados de rastreamento para um servidor Zipkin, que armazena e visualiza essas informações.
      - O `zipkin-reporter-brave` atua como um componente de relatório, ajudando a enviar esses dados para o servidor Zipkin.

**Resumo:**
Essas dependências são úteis quando deseja implementar rastreamento distribuído em seus aplicativos Java. Ao utilizar o Brave como uma biblioteca de rastreamento distribuído e o Zipkin como um servidor para armazenar e visualizar esses rastreamentos, você pode ganhar insights valiosos sobre o comportamento e o desempenho de suas aplicações distribuídas. O Micrometer, neste contexto, ajuda a coletar métricas relacionadas ao rastreamento para fins de monitoramento e análise de desempenho.

## Configurando no servidor config-server no arquivo de configuração `department-service.yaml` o zipkin

Adicionando o serviço do Zipkin para gerar a probabilidade de amostragem de rastreamento de gerenciamento do departamento. O objetivo é fazer com que o serviço de departamento publique todos os seus dados para o Zipkin.

```yaml
management:
  tracing:
    sampling:
      probability: 1.0
```

Essa configuração está relacionada ao rastreamento distribuído usado em ambientes de microserviços, para monitorar e analisar o fluxo de solicitações entre diferentes serviços. 

Analisando cada parte dessa configuração:

1. **`management`**:
   - O prefixo `management` refere-se a configurações de gerenciamento e monitoramento no contexto de um aplicativo Spring Boot.

2. **`tracing`**:
   - O subpacote `tracing` é específico para configurações relacionadas ao rastreamento.

3. **`sampling`**:
   - O subpacote `sampling` lida com a amostragem de rastreamentos. Amostragem é o processo de decidir quais solicitações devem ser incluídas no rastreamento e quais não.

4. **`probability: 1.0`**:
   - A configuração `probability` define a probabilidade de amostragem. Neste caso, está configurada como `1.0`, o que significa que todas as solicitações serão amostradas.

   - O valor `1.0` indica uma probabilidade de 100%. Em outras palavras, todas as solicitações serão incluídas no rastreamento.

   - Se você definisse `probability` como `0.5`, seria uma probabilidade de 50%, o que significaria que metade das solicitações seria amostrada aleatoriamente.

   - A amostragem é frequentemente usada para limitar o volume de dados de rastreamento, especialmente em ambientes de produção, onde pode ser impraticável ou indesejável rastrear cada solicitação.

**Resumo:**
Essa configuração específica indica que todas as solicitações devem ser amostradas para rastreamento distribuído. Isso significa que o sistema de rastreamento (como Zipkin ou Jaeger) irá coletar e armazenar informações sobre todas as solicitações que passam pelo aplicativo. Em ambientes de produção, isso pode gerar uma grande quantidade de dados, e a amostragem é uma maneira de controlar esse volume. Ao definir a probabilidade de amostragem como `1.0`, todas as solicitações são incluídas no rastreamento.