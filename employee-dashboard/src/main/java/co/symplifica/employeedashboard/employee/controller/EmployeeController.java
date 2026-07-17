package co.symplifica.employeedashboard.employee.controller;

import co.symplifica.employeedashboard.employee.dto.EmployeeDetailResponse;
import co.symplifica.employeedashboard.employee.dto.EmployeeRequest;
import co.symplifica.employeedashboard.employee.dto.EmployeeResponse;
import co.symplifica.employeedashboard.employee.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<EmployeeResponse> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @PostMapping
    public EmployeeResponse createEmployee(
            @Valid @RequestBody EmployeeRequest request) {

        return employeeService.createEmployee(request);
    }

    @GetMapping("/{id}")
    public EmployeeResponse getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }

    @GetMapping("/{id}/details")
    public EmployeeDetailResponse getEmployeeDetails(
            @PathVariable Long id) {

        return employeeService.getEmployeeDetails(id);

    }

    @PutMapping("/{id}")
    public EmployeeResponse updateEmployee(
            @PathVariable Long id,
            @Valid @RequestBody EmployeeRequest request) {

        return employeeService.updateEmployee(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id) {

        employeeService.deleteEmployee(id);
    }
}