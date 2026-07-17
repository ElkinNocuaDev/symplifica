package co.symplifica.employeedashboard.location.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class NominatimClient {

    private final RestClient restClient;

    public NominatimClient() {

        this.restClient = RestClient.builder()
                .baseUrl("https://nominatim.openstreetmap.org")
                .defaultHeader(
                        "User-Agent",
                        "employee-dashboard"
                )
                .build();

    }

    public String searchCity(String city) {

        return restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/search")
                        .queryParam("q", city)
                        .queryParam("format", "xml")
                        .build())
                .retrieve()
                .body(String.class);

    }

}