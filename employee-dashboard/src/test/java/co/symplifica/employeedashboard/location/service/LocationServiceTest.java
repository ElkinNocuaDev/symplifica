package co.symplifica.employeedashboard.location.service;

import co.symplifica.employeedashboard.location.client.NominatimClient;
import co.symplifica.employeedashboard.location.dto.LocationResponse;
import co.symplifica.employeedashboard.location.parser.XmlLocationParser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LocationServiceTest {

    @Mock
    private NominatimClient nominatimClient;

    @Mock
    private XmlLocationParser parser;

    @InjectMocks
    private LocationService locationService;

    @Test
    void shouldReturnLocationByCity() {

        String xml = "<searchresults></searchresults>";

        LocationResponse response = LocationResponse.builder()
                .displayName("Bogotá, Colombia")
                .latitude(4.6533817)
                .longitude(-74.0836331)
                .build();

        when(nominatimClient.searchCity("Bogotá"))
                .thenReturn(xml);

        when(parser.parse(xml))
                .thenReturn(response);

        LocationResponse result =
                locationService.getLocationByCity("Bogotá");

        assertNotNull(result);
        assertEquals("Bogotá, Colombia", result.getDisplayName());
        assertEquals(4.6533817, result.getLatitude());
        assertEquals(-74.0836331, result.getLongitude());

        verify(nominatimClient).searchCity("Bogotá");
        verify(parser).parse(xml);
    }

    @Test
    void shouldCallParserWithReturnedXml() {

        String xml = "<xml>test</xml>";

        LocationResponse response = LocationResponse.builder().build();

        when(nominatimClient.searchCity("Medellín"))
                .thenReturn(xml);

        when(parser.parse(xml))
                .thenReturn(response);

        locationService.getLocationByCity("Medellín");

        verify(nominatimClient).searchCity("Medellín");
        verify(parser).parse(xml);
    }

}