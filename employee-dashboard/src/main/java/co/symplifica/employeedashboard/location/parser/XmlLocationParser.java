package co.symplifica.employeedashboard.location.parser;

import co.symplifica.employeedashboard.location.dto.LocationResponse;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

@Component
public class XmlLocationParser {

    public LocationResponse parse(String xml) {

        try {

            Document document = DocumentBuilderFactory
                    .newInstance()
                    .newDocumentBuilder()
                    .parse(new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8)));

            Element place = (Element) document
                    .getElementsByTagName("place")
                    .item(0);

            if (place == null) {
                return null;
            }

            return LocationResponse.builder()
                    .displayName(place.getAttribute("display_name"))
                    .latitude(Double.valueOf(place.getAttribute("lat")))
                    .longitude(Double.valueOf(place.getAttribute("lon")))
                    .build();

        } catch (Exception e) {
            throw new RuntimeException("Error parsing XML response", e);
        }

    }

}