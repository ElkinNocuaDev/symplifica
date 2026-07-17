package co.symplifica.employeedashboard.benefit.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class BenefitResponse {

    private Long id;

    private String benefitName;

    private BigDecimal amount;

    private Long employeeId;
}