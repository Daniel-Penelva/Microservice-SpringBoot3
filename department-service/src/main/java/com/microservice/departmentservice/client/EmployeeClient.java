package com.microservice.departmentservice.client;

import com.microservice.departmentservice.model.Employee;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import java.util.List;

@HttpExchange
public interface EmployeeClient {

    @GetExchange("api/employee/department/{departmentId}")
    public List<Employee> findByDepartment(@PathVariable("departmentId") Long departmentId);

}

/* Precisamos conseguir o(s) funcionario(s) de um determinado departamento, até um momento a API employee-service só enxerga a relação com o departamento,
porém o departamento ainda não enxerga a relação com os funcionários, ou seja, podemos criar um funcionario já amarrando a um departamento, porém
quando buscamos o departamento não conseguimos fornecer os funcionarios asscociado a esse departamento. Logo, eu preciso de uma API que me forneça todos
os departamentos e todos os seus funcionários, isto é, tenho que fazer com que cada service do departamento (microservice API "departament-service")
tenha uma ligação para com o service do funcionario (microservice API "employee-service"), portanto tenho que obter todos os funcionários pertencentes
a esse ID de departamento.

@HttpExchange: Indica que a interface é um contrato para chamadas HTTP.

Em resumo, essa interface EmployeeClient serve como uma especificação do contrato para as chamadas HTTP ao serviço de api/employee. O Feign, ao ser
configurado corretamente, usará essas informações para gerar automaticamente implementações concretas dessas chamadas, facilitando a comunicação com o
serviço remoto.
* */