package in.task.service;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.CSVReader;

import in.task.entity.City;
import in.task.entity.Country;
import in.task.entity.State;
import in.task.repo.CityRepository;
import in.task.repo.CountryRepository;
import in.task.repo.StateRepository;

@Service
public class CityService {
    @Autowired
    private CityRepository cityRepository;
    

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private CountryRepository countryRepository;
    

    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    public City saveCity(City city) {
        return cityRepository.save(city);
    }
    
    //new code for dropdown
    
    public List<City> getCitiesByStateCode(String stateCode) {
        return cityRepository.findByStateStateCode(stateCode); // Custom query method to get cities by state code
    }
    
    
    // if not worked this code should be deleted
    
    public void saveFromCSV(MultipartFile file) throws Exception {
        Map<String, State> stateMap = new HashMap<>();
        stateRepository.findAll().forEach(state -> stateMap.put(state.getStateCode(), state));

        Map<String, Country> countryMap = new HashMap<>();
        countryRepository.findAll().forEach(country -> countryMap.put(country.getCountryCode(), country));

        List<City> cities = parseCSV(file, stateMap, countryMap);
        cityRepository.saveAll(cities);
    }
    
     private List<City> parseCSV(MultipartFile file, Map<String, State> stateMap, Map<String, Country> countryMap) throws Exception {
        List<City> cities = new ArrayList<>();
        try (CSVReader csvReader = new CSVReader(new InputStreamReader(file.getInputStream()))) {
            String[] nextLine;
            boolean isFirstLine = true;

            while ((nextLine = csvReader.readNext()) != null) {
                // Skip the header row
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

               
                if (nextLine[0] == null || nextLine[1] == null || nextLine[2] == null || nextLine[3] == null) {
                    // Log the issue and skip the row
                    System.err.println("Skipping row with null values: " + Arrays.toString(nextLine));
                    continue;
                }

                City city = new City();
                city.setCityCode(nextLine[0].trim());
                city.setCityName(nextLine[1].trim());
                city.setState(stateMap.get(nextLine[2].trim()));   // Map stateCode to State object
                city.setCountry(countryMap.get(nextLine[3].trim())); // Map countryCode to Country object

                if (city.getState() == null || city.getCountry() == null) {
                    System.err.println("Skipping row due to missing state or country mapping: " + Arrays.toString(nextLine));
                    continue;
                }

                cities.add(city);
            }
        }
        return cities;
    }

    
}

