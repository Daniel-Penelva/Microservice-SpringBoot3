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

/* Dentro desse pacote de configuração a classe "WebClientConfig" será responsável por sempre dizer ao cliente funcionario (employee) que deve
* criar um bean de client web, isso fará com que aponte para o serviço do funcionário.
*
* O método employeeWebClient() faz com que o client web se conecte com o meu service de funcionario (employee) na porta 8082.
*
* O método  employeeClient() estará anexando os serviços web de funcionário (employee) ao client web e o client web se conectará a este serviço
* funcionário. */