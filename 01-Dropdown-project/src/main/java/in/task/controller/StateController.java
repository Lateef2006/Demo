package in.task.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.task.entity.State;
import in.task.repo.StateRepository;
import in.task.service.StateService;

@RestController
@RequestMapping("/states")
public class StateController {
    @Autowired
    private StateService stateService;

    @Autowired
    private StateRepository stateRepository;
    
 

    @GetMapping("/getallstates")
    public List<State> getStatesByCountryCode(String countryCode) {
        return stateRepository.findByCountryCountryCode(countryCode); // Custom query method to get states by country code
    }


    @PostMapping
    public State addState(@RequestBody State state) {
        return stateService.saveState(state);
    }
}

