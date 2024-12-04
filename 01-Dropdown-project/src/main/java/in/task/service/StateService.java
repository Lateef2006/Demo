package in.task.service;



import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.CSVReader;

import in.task.entity.Country;
import in.task.entity.State;
import in.task.repo.CountryRepository;
import in.task.repo.StateRepository;

@Service
public class StateService {
    @Autowired
    private StateRepository stateRepository;
    
    @Autowired
    private CountryRepository countryRepository;
    
    //new code added for dropdown
    
    public List<State> getStatesByCountryCode(String countryCode) {
        return stateRepository.findByCountryCountryCode(countryCode); // Custom query method to get states by country code
    }


    public List<State> getAllStates() {
        return stateRepository.findAll();
    }

    public State saveState(State state) {
        return stateRepository.save(state);
    }
   
    
    public void saveFromCSV(MultipartFile file) throws Exception {
        // Create a map of countryCode to Country
        Map<String, Country> countryMap = new HashMap<>();
        countryRepository.findAll().forEach(country -> countryMap.put(country.getCountryCode(), country));

        // Parse CSV with countryMap
        List<State> states = parseCSV(file, countryMap);
        stateRepository.saveAll(states);
    }
    
	
    private List<State> parseCSV(MultipartFile file, Map<String, Country> countryMap) throws Exception {
        List<State> states = new ArrayList<>();
        try (CSVReader csvReader = new CSVReader(new InputStreamReader(file.getInputStream()))) {
            String[] nextLine;
            boolean isFirstLine = true;

            while ((nextLine = csvReader.readNext()) != null) {
                // Skip the header row
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                // Assuming CSV structure: stateCode, stateName, countryCode
                State state = new State();
                state.setStateCode(nextLine[0].trim());
                state.setStateName(nextLine[1].trim());
                state.setCountry(countryMap.get(nextLine[2].trim())); // Map countryCode to Country object

                states.add(state);
            }
        }
        return states;
    }

    
}


