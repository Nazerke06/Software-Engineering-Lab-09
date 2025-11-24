package com.example.demo.controller;

import com.example.demo.entity.Country;
import com.example.demo.repository.CountryRepository;
import com.example.demo.services.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/travellers")
public class CountryController {

    private CountryRepository countyRepository;

    private CountryService countryService;

    @GetMapping
    public ResponseEntity<?> getCountries() {
        List<Country> countryList = countyRepository.findAll();
        if(countryList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else{
            return new ResponseEntity<>(countryList, HttpStatus.OK);
        }
    }
}
