package co.symplifica.employeedashboard.location.controller;

import co.symplifica.employeedashboard.location.dto.LocationResponse;
import co.symplifica.employeedashboard.location.service.LocationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/locations")
public class LocationController {

    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping
    public LocationResponse search(@RequestParam String city) {

        return locationService.getLocationByCity(city);

    }

}