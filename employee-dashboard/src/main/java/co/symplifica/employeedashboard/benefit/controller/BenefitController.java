package co.symplifica.employeedashboard.benefit.controller;

import co.symplifica.employeedashboard.benefit.dto.BenefitRequest;
import co.symplifica.employeedashboard.benefit.dto.BenefitResponse;
import co.symplifica.employeedashboard.benefit.service.BenefitService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/benefits")
public class BenefitController {

    private final BenefitService benefitService;

    public BenefitController(BenefitService benefitService) {
        this.benefitService = benefitService;
    }

    @GetMapping("/employee/{employeeId}")
    public List<BenefitResponse> getBenefitsByEmployee(
            @PathVariable Long employeeId) {

        return benefitService.getBenefitsByEmployee(employeeId);
    }

    @PostMapping
    public BenefitResponse createBenefit(
            @Valid @RequestBody BenefitRequest request) {

        return benefitService.createBenefit(request);
    }

    @PutMapping("/{id}")
    public BenefitResponse updateBenefit(
            @PathVariable Long id,
            @Valid @RequestBody BenefitRequest request) {

        return benefitService.updateBenefit(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteBenefit(@PathVariable Long id) {

        benefitService.deleteBenefit(id);
    }
}