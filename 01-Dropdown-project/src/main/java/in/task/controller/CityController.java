package in.task.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.task.entity.City;
import in.task.service.CityService;

@RestController
@RequestMapping("/cities")
public class CityController {
    @Autowired
    private CityService cityService;


    
   
    @GetMapping("/getallcities")
    public List<City> getAllCities(@RequestParam String stateCode) {
        return cityService.getCitiesByStateCode(stateCode); // Custom method to fetch cities by state code
    }

    @PostMapping
    public City addCity(@RequestBody City city) {
        return cityService.saveCity(city);
    }
}

