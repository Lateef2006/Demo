package in.task.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import in.task.entity.City;
import in.task.entity.Country;
import in.task.entity.State;
import in.task.service.CityService;
import in.task.service.CountryService;
import in.task.service.StateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/upload-files")
public class UploadController {

    @Autowired
    private CountryService countryService;

    @Autowired
    private StateService stateService;

    @Autowired
    private CityService cityService;
    
    @GetMapping("/upload-form")
    public String showUploadForm() {
        return "index"; 
    }


    @PostMapping
    public String handleFileUpload(
            @RequestParam("countryFile") MultipartFile countryFile,
            @RequestParam("stateFile") MultipartFile stateFile,
            @RequestParam("cityFile") MultipartFile cityFile) {
        try {
        	
            countryService.saveFromCSV(countryFile);
            
            stateService.saveFromCSV(stateFile);
           
            cityService.saveFromCSV(cityFile);

           
            return "dropdown"; 
        } catch (Exception e) {
            e.printStackTrace();
            return "error"; 
        }
    }
}


