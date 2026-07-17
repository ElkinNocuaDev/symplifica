package co.symplifica.employeedashboard.employee.dto;

import co.symplifica.employeedashboard.benefit.dto.BenefitResponse;
import co.symplifica.employeedashboard.location.dto.LocationResponse;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class EmployeeDetailResponse {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String city;

    private LocationResponse location;

    private List<BenefitResponse> benefits;
}