package co.symplifica.employeedashboard.employee.service;

import co.symplifica.employeedashboard.benefit.dto.BenefitResponse;
import co.symplifica.employeedashboard.benefit.service.BenefitService;
import co.symplifica.employeedashboard.employee.dto.EmployeeDetailResponse;
import co.symplifica.employeedashboard.employee.dto.EmployeeRequest;
import co.symplifica.employeedashboard.employee.dto.EmployeeResponse;
import co.symplifica.employeedashboard.employee.entity.Employee;
import co.symplifica.employeedashboard.employee.repository.EmployeeRepository;
import co.symplifica.employeedashboard.location.dto.LocationResponse;
import co.symplifica.employeedashboard.location.service.LocationService;
import org.springframework.stereotype.Service;
import co.symplifica.employeedashboard.exception.EmployeeNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final BenefitService benefitService;
    private final LocationService locationService;

    public EmployeeService(
            EmployeeRepository employeeRepository,
            BenefitService benefitService,
            LocationService locationService) {

        this.employeeRepository = employeeRepository;
        this.benefitService = benefitService;
        this.locationService = locationService;
    }

    public List<EmployeeResponse> getAllEmployees() {

        return employeeRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());

    }

    public EmployeeResponse createEmployee(EmployeeRequest request) {

        Employee employee = Employee.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .city(request.getCity())
                .build();

        Employee saved = employeeRepository.save(employee);

        return toResponse(saved);

    }

    public EmployeeResponse getEmployeeById(Long id) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));

        return toResponse(employee);

    }

    public EmployeeDetailResponse getEmployeeDetails(Long id) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));

        List<BenefitResponse> benefits =
                benefitService.getBenefitsByEmployee(id);

        LocationResponse location =
                locationService.getLocationByCity(employee.getCity());

        return EmployeeDetailResponse.builder()
                .id(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .email(employee.getEmail())
                .city(employee.getCity())
                .location(location)
                .benefits(benefits)
                .build();

    }

    private EmployeeResponse toResponse(Employee employee) {

        return EmployeeResponse.builder()
                .id(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .email(employee.getEmail())
                .city(employee.getCity())
                .build();

    }

    public EmployeeResponse updateEmployee(Long id, EmployeeRequest request) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));

        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setEmail(request.getEmail());
        employee.setCity(request.getCity());

        Employee updated = employeeRepository.save(employee);

        return toResponse(updated);
    }

    public void deleteEmployee(Long id) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));

        employeeRepository.delete(employee);
    }

}