package com.google.shinyay.client.controller

import com.google.shinyay.client.model.Employee
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.ParameterizedTypeReference
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.reactive.function.client.WebClient

@Controller
class EmployeeClientController(val webClient: WebClient) {

    @Value("\${resourceserver.api.url}")
    lateinit var employeeApiUrl: String

    @GetMapping("/employees")
    fun findAllEmployees(model: Model): String {
        val employees = webClient.get()
            .uri(employeeApiUrl)
            .retrieve()
            .bodyToMono(object : ParameterizedTypeReference<List<Employee>>() {})
            .block()
        model.addAttribute("employees", employees)
        return "employees"
    }
}