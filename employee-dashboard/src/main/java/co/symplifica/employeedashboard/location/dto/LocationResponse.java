package co.symplifica.employeedashboard.location.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LocationResponse {

    private String displayName;

    private Double latitude;

    private Double longitude;

}