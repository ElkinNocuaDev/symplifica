package co.symplifica.employeedashboard.benefit.service;

import co.symplifica.employeedashboard.benefit.dto.BenefitRequest;
import co.symplifica.employeedashboard.benefit.dto.BenefitResponse;
import co.symplifica.employeedashboard.benefit.entity.Benefit;
import co.symplifica.employeedashboard.benefit.repository.BenefitRepository;
import co.symplifica.employeedashboard.employee.entity.Employee;
import co.symplifica.employeedashboard.employee.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import co.symplifica.employeedashboard.exception.BenefitNotFoundException;
import co.symplifica.employeedashboard.exception.EmployeeNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BenefitService {

    private final BenefitRepository benefitRepository;
    private final EmployeeRepository employeeRepository;

    public BenefitService(BenefitRepository benefitRepository,
                          EmployeeRepository employeeRepository) {
        this.benefitRepository = benefitRepository;
        this.employeeRepository = employeeRepository;
    }

    public List<BenefitResponse> getBenefitsByEmployee(Long employeeId) {

        return benefitRepository.findByEmployeeId(employeeId)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public BenefitResponse createBenefit(BenefitRequest request) {

        Employee employee = employeeRepository.findById(request.getEmployeeId())
                .orElseThrow(() -> new EmployeeNotFoundException(request.getEmployeeId()));

        Benefit benefit = Benefit.builder()
                .benefitName(request.getBenefitName())
                .amount(request.getAmount())
                .employee(employee)
                .build();

        Benefit saved = benefitRepository.save(benefit);

        return toResponse(saved);
    }

    public BenefitResponse updateBenefit(Long id, BenefitRequest request) {

        Benefit benefit = benefitRepository.findById(id)
                .orElseThrow(() -> new BenefitNotFoundException(id));

        Employee employee = employeeRepository.findById(request.getEmployeeId())
                .orElseThrow(() -> new EmployeeNotFoundException(request.getEmployeeId()));

        benefit.setBenefitName(request.getBenefitName());
        benefit.setAmount(request.getAmount());
        benefit.setEmployee(employee);

        Benefit updated = benefitRepository.save(benefit);

        return toResponse(updated);
    }

    public void deleteBenefit(Long id) {

        Benefit benefit = benefitRepository.findById(id)
                .orElseThrow(() -> new BenefitNotFoundException(id));

        benefitRepository.delete(benefit);
    }

    private BenefitResponse toResponse(Benefit benefit) {

        return BenefitResponse.builder()
                .id(benefit.getId())
                .benefitName(benefit.getBenefitName())
                .amount(benefit.getAmount())
                .employeeId(benefit.getEmployee().getId())
                .build();
    }
}