package co.symplifica.employeedashboard.location.service;

import co.symplifica.employeedashboard.location.client.NominatimClient;
import co.symplifica.employeedashboard.location.dto.LocationResponse;
import co.symplifica.employeedashboard.location.parser.XmlLocationParser;
import org.springframework.stereotype.Service;

@Service
public class LocationService {

    private final NominatimClient nominatimClient;
    private final XmlLocationParser parser;

    public LocationService(
            NominatimClient nominatimClient,
            XmlLocationParser parser) {

        this.nominatimClient = nominatimClient;
        this.parser = parser;
    }

    public LocationResponse getLocationByCity(String city) {

        String xml = nominatimClient.searchCity(city);

        return parser.parse(xml);

    }

}