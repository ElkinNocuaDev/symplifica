package co.symplifica.employeedashboard.benefit.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BenefitRequest {

    @NotNull
    private Long employeeId;

    @NotBlank
    private String benefitName;

    @NotNull
    @DecimalMin("0.0")
    private BigDecimal amount;
}