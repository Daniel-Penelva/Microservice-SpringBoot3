# Anotação `@EnableConfigServer`

A anotação `@EnableConfigServer` é uma anotação específica do Spring Cloud que é usada para habilitar a funcionalidade de um servidor de configuração em um aplicativo Spring Boot. Esse servidor de configuração é muitas vezes utilizado em arquiteturas de microserviços para centralizar e fornecer configurações para vários serviços.

Principais características e funções dessa anotação:

1. **Habilita o Servidor de Configuração:**
   Ao adicionar a anotação `@EnableConfigServer` a uma classe de configuração do Spring Boot está indicando que esse aplicativo será configurado como um servidor de configuração.

   Exemplo:

   ```java
   import org.springframework.cloud.config.server.EnableConfigServer;
   import org.springframework.boot.SpringApplication;
   import org.springframework.boot.autoconfigure.SpringBootApplication;

   @SpringBootApplication
   @EnableConfigServer
   public class ConfigServerApplication {
       public static void main(String[] args) {
           SpringApplication.run(ConfigServerApplication.class, args);
       }
   }
   ```

2. **Fornece Configurações Centralizadas:**
   Um servidor de configuração permite que você armazene configurações em um local centralizado, geralmente em um repositório Git ou outro sistema de armazenamento de configuração. Os clientes (outros microserviços) podem então acessar essas configurações dinamicamente.

3. **Integração com Spring Cloud Config:**
   A anotação `@EnableConfigServer` funciona em conjunto com o projeto Spring Cloud Config, que fornece uma solução abrangente para a gestão centralizada de configurações em ambientes de microserviços.

4. **Exemplo de Configuração no `application.yml`:**
   Aqui está um exemplo de configuração típica em `application.yml` para um aplicativo configurado como um servidor de configuração:

   ```yaml
   server:
     port: 8888

   spring:
     cloud:
       config:
         server:
           git:
             uri: https://github.com/seu-usuario/seu-repositorio.git
             search-paths: repositorio-de-configuracoes
   ```

   Este exemplo configura o servidor de configuração para escutar na porta 8888 e acessar um repositório Git hospedado em https://github.com/seu-usuario/seu-repositorio.git. As configurações específicas estão no diretório `repositorio-de-configuracoes`.

Em resumo, `@EnableConfigServer` é uma anotação crucial para transformar um aplicativo Spring Boot em um servidor de configuração que gerencia e distribui configurações para outros serviços em um ambiente de microserviços.

# Configuração `application.yml`

O arquivo de configuração YAML para um aplicativo Spring Boot e inclui configurações relacionadas ao servidor embutido e aos profiles do Spring. 
Analisando cada parte do script:

1. **Configuração do Servidor embutido do Spring Boot:**
   ```yaml
   server:
     port: 8088
   ```
   Aqui, está sendo configurada a porta em que o servidor embutido do Spring Boot deve escutar. O aplicativo será acessível através da porta 8088.

2. **Configuração de Profiles do Spring:**
   ```yaml
   spring:
     profiles:
       active: native
   ```
   Nesta seção, está sendo configurado o profile ativo do Spring. O profile determina quais configurações específicas do ambiente serão carregadas. No caso, o profile ativo é definido como "native".

   - `spring.profiles.active`: Indica qual profile deve ser ativado. No caso, "native" é especificado como o profile ativo.

   Essa configuração é particularmente útil quando se trabalha com diferentes ambientes (por exemplo, desenvolvimento, teste, produção) e você deseja ter configurações específicas para cada um.

   No contexto do profile "native", é comum ver essa configuração quando se usa o Spring Cloud Config com um repositório de configuração local (como um diretório no sistema de arquivos). O profile "native" pode ser usado para indicar que as configurações estão no ambiente local, facilitando o desenvolvimento e testes locais.

Exemplo de configuração usando o Spring Cloud Config e repositório local (em um arquivo `bootstrap.yml`):

```yaml
spring:
  cloud:
    config:
      uri: file://${user.home}/config-repo
```

No exemplo acima, o URI do repositório de configuração é definido como um diretório local no sistema de arquivos.

Em resumo, este script configura um aplicativo Spring Boot para escutar na porta 8088 e ativa o profile "native", que pode ser associado a configurações específicas do ambiente local, especialmente quando usado com o Spring Cloud Config.

# Configuração `department-service.yaml`

O arquivo de configuração YAML usado para configurar um aplicativo Spring Boot para interagir com o serviço de descoberta Eureka. ]
Analisando cada parte do script:

1. **Configuração do Servidor embutido do Spring Boot:**
   ```yaml
   server:
     port: 8081
   ```
   Aqui, está sendo configurada a porta em que o servidor embutido do Spring Boot deve escutar. O aplicativo será acessível através da porta 8081.

2. **Configuração do Cliente Eureka:**
   ```yaml
   eureka:
     client:
       serviceUrl:
         defaultZone: http://localhost:8761/eureka/
   ```
   Nesta seção, está sendo configurado o cliente Eureka para interagir com o servidor Eureka. Aqui estão os detalhes:

   - `eureka.client`: Configurações relacionadas ao cliente Eureka.
   - `serviceUrl.defaultZone`: Especifica a URL do servidor Eureka. Neste caso, está configurado para o servidor Eureka na mesma máquina (`localhost`) e na porta padrão (`8761`).

Essa configuração é típica em arquiteturas de microserviços, onde o Eureka é usado para registrar e descobrir dinamicamente serviços na rede. O servidor Eureka (`http://localhost:8761/eureka/` no exemplo) é onde outros serviços podem consultar para descobrir informações sobre os serviços disponíveis.

Em resumo, este script configura um aplicativo Spring Boot para escutar na porta 8081 e interagir com um servidor Eureka localizado em http://localhost:8761/eureka/. Essa integração facilita a descoberta dinâmica de serviços em ambientes distribuídos.