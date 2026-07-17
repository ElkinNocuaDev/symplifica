package co.symplifica.employeedashboard.employee.service;

import co.symplifica.employeedashboard.employee.dto.EmployeeResponse;
import co.symplifica.employeedashboard.employee.entity.Employee;
import co.symplifica.employeedashboard.employee.repository.EmployeeRepository;
import co.symplifica.employeedashboard.benefit.service.BenefitService;
import co.symplifica.employeedashboard.location.service.LocationService;
import co.symplifica.employeedashboard.employee.dto.EmployeeRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private BenefitService benefitService;

    @Mock
    private LocationService locationService;

    @InjectMocks
    private EmployeeService employeeService;

    @Test
    void shouldReturnAllEmployees() {

        Employee employee = Employee.builder()
                .id(1L)
                .firstName("Elkin")
                .lastName("Nocua")
                .email("elkin@test.com")
                .city("Bogotá")
                .build();

        when(employeeRepository.findAll())
                .thenReturn(List.of(employee));

        List<EmployeeResponse> response =
                employeeService.getAllEmployees();

        assertEquals(1, response.size());
        assertEquals("Elkin", response.get(0).getFirstName());
        assertEquals("Bogotá", response.get(0).getCity());

    }

    @Test
    void shouldReturnEmployeeById() {

        Employee employee = Employee.builder()
                .id(1L)
                .firstName("Elkin")
                .lastName("Nocua")
                .email("elkin@test.com")
                .city("Bogotá")
                .build();

        when(employeeRepository.findById(1L))
                .thenReturn(java.util.Optional.of(employee));

        EmployeeResponse response = employeeService.getEmployeeById(1L);

        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("Elkin", response.getFirstName());
    }

    @Test
    void shouldCreateEmployee() {

        EmployeeRequest request = EmployeeRequest.builder()
                .firstName("Carlos")
                .lastName("Perez")
                .email("carlos@test.com")
                .city("Medellín")
                .build();

        Employee employee = Employee.builder()
                .id(2L)
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .city(request.getCity())
                .build();

        when(employeeRepository.save(org.mockito.ArgumentMatchers.any(Employee.class)))
                .thenReturn(employee);

        EmployeeResponse response = employeeService.createEmployee(request);

        assertEquals(2L, response.getId());
        assertEquals("Carlos", response.getFirstName());
        assertEquals("Medellín", response.getCity());
    }

    @Test
    void shouldUpdateEmployee() {

        Employee employee = Employee.builder()
                .id(1L)
                .firstName("Elkin")
                .lastName("Nocua")
                .email("old@test.com")
                .city("Bogotá")
                .build();

        EmployeeRequest request = EmployeeRequest.builder()
                .firstName("Elkin Manuel")
                .lastName("Nocua")
                .email("new@test.com")
                .city("Medellín")
                .build();

        when(employeeRepository.findById(1L))
                .thenReturn(java.util.Optional.of(employee));

        when(employeeRepository.save(employee))
                .thenReturn(employee);

        EmployeeResponse response =
                employeeService.updateEmployee(1L, request);

        assertEquals("Elkin Manuel", response.getFirstName());
        assertEquals("Medellín", response.getCity());
    }

    @Test
    void shouldDeleteEmployee() {

        Employee employee = Employee.builder()
                .id(1L)
                .firstName("Elkin")
                .build();

        when(employeeRepository.findById(1L))
                .thenReturn(java.util.Optional.of(employee));

        employeeService.deleteEmployee(1L);

        org.mockito.Mockito.verify(employeeRepository)
                .delete(employee);
    }

    @Test
    void shouldThrowExceptionWhenEmployeeNotFound() {

        when(employeeRepository.findById(100L))
                .thenReturn(java.util.Optional.empty());

        assertThrows(
                co.symplifica.employeedashboard.exception.EmployeeNotFoundException.class,
                () -> employeeService.getEmployeeById(100L)
        );
    }

}