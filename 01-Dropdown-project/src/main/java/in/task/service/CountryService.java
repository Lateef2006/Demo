package in.task.service;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.CSVReader;

import in.task.entity.Country;
import in.task.repo.CountryRepository;

@Service
public class CountryService {
    @Autowired
    private CountryRepository countryRepository;

    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    public Country saveCountry(Country country) {
        return countryRepository.save(country);
    }
    
   
    public void saveFromCSV(MultipartFile file) throws Exception {
        
        List<Country> countries = parseCSV(file);
        countryRepository.saveAll(countries);
    }
    
    private List<Country> parseCSV(MultipartFile file) throws Exception {
        List<Country> countries = new ArrayList<>();
        try (CSVReader csvReader = new CSVReader(new InputStreamReader(file.getInputStream()))) {
            String[] nextLine;
            boolean isFirstLine = true;

            while ((nextLine = csvReader.readNext()) != null) {
               
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

              
                Country country = new Country();
                country.setCountryCode(nextLine[0].trim());
                country.setCountryName(nextLine[1].trim());

                countries.add(country);
            }
        }
        return countries;
    }
    
}

