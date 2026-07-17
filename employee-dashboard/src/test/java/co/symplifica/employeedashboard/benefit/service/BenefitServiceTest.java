package co.symplifica.employeedashboard.benefit.service;

import co.symplifica.employeedashboard.benefit.dto.BenefitRequest;
import co.symplifica.employeedashboard.benefit.dto.BenefitResponse;
import co.symplifica.employeedashboard.benefit.entity.Benefit;
import co.symplifica.employeedashboard.benefit.repository.BenefitRepository;
import co.symplifica.employeedashboard.employee.entity.Employee;
import co.symplifica.employeedashboard.employee.repository.EmployeeRepository;
import co.symplifica.employeedashboard.exception.BenefitNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BenefitServiceTest {

    @Mock
    private BenefitRepository benefitRepository;

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private BenefitService benefitService;

    @Test
    void shouldReturnBenefitsByEmployee() {

        Employee employee = Employee.builder()
                .id(1L)
                .build();

        Benefit benefit = Benefit.builder()
                .id(1L)
                .benefitName("Health Insurance")
                .amount(BigDecimal.valueOf(500000))
                .employee(employee)
                .build();

        when(benefitRepository.findByEmployeeId(1L))
                .thenReturn(List.of(benefit));

        List<BenefitResponse> response =
                benefitService.getBenefitsByEmployee(1L);

        assertEquals(1, response.size());
        assertEquals("Health Insurance", response.get(0).getBenefitName());
    }

    @Test
    void shouldCreateBenefit() {

        Employee employee = Employee.builder()
                .id(1L)
                .build();

        BenefitRequest request = BenefitRequest.builder()
                .benefitName("Dental Plan")
                .amount(BigDecimal.valueOf(300000))
                .employeeId(1L)
                .build();

        Benefit benefit = Benefit.builder()
                .id(2L)
                .benefitName(request.getBenefitName())
                .amount(request.getAmount())
                .employee(employee)
                .build();

        when(employeeRepository.findById(1L))
                .thenReturn(Optional.of(employee));

        when(benefitRepository.save(any(Benefit.class)))
                .thenReturn(benefit);

        BenefitResponse response =
                benefitService.createBenefit(request);

        assertEquals(2L, response.getId());
        assertEquals("Dental Plan", response.getBenefitName());
    }

    @Test
    void shouldUpdateBenefit() {

        Employee employee = Employee.builder()
                .id(1L)
                .build();

        Benefit benefit = Benefit.builder()
                .id(1L)
                .benefitName("Old Benefit")
                .amount(BigDecimal.valueOf(100000))
                .employee(employee)
                .build();

        BenefitRequest request = BenefitRequest.builder()
                .benefitName("New Benefit")
                .amount(BigDecimal.valueOf(200000))
                .employeeId(1L)
                .build();

        when(benefitRepository.findById(1L))
                .thenReturn(Optional.of(benefit));

        when(employeeRepository.findById(1L))
                .thenReturn(Optional.of(employee));

        when(benefitRepository.save(benefit))
                .thenReturn(benefit);

        BenefitResponse response =
                benefitService.updateBenefit(1L, request);

        assertEquals("New Benefit", response.getBenefitName());
        assertEquals(BigDecimal.valueOf(200000), response.getAmount());
    }

    @Test
    void shouldDeleteBenefit() {

        Benefit benefit = Benefit.builder()
                .id(1L)
                .build();

        when(benefitRepository.findById(1L))
                .thenReturn(Optional.of(benefit));

        benefitService.deleteBenefit(1L);

        verify(benefitRepository).delete(benefit);
    }

    @Test
    void shouldThrowBenefitNotFoundException() {

        when(benefitRepository.findById(999L))
                .thenReturn(Optional.empty());

        assertThrows(
                BenefitNotFoundException.class,
                () -> benefitService.deleteBenefit(999L)
        );
    }

}