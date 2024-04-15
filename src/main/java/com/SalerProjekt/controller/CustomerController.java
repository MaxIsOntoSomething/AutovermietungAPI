package com.SalerProjekt.controller;

import com.SalerProjekt.dto.BookACarDto;
import com.SalerProjekt.dto.CarDto;
import com.SalerProjekt.dto.SearchCarDto;
import com.SalerProjekt.services.customer.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;


    // Alle Autos auflisten
    @GetMapping("/cars")
    public ResponseEntity<List<CarDto>> getAllCars(){
        List<CarDto> carDtoList = customerService.getAllCars();
        return ResponseEntity.ok(carDtoList);
    }

    // Auto buchen
    @PostMapping("/car/book/{carId}")
    public ResponseEntity<Void> bookACar(@PathVariable Long carId, @RequestBody BookACarDto bookACarDto){
        boolean success = customerService.bookACar(carId, bookACarDto);
        if (success){
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // Auto nach ID suchen
    @GetMapping("/car/{carId}")
    public ResponseEntity<CarDto> getCarById(@PathVariable Long carId){
        CarDto carDto = customerService.getCarById(carId);
        if (carDto == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(carDto);
    }

    // Alle Buchungen auflisten
    @GetMapping("/car/booked/{userId}")
    public ResponseEntity<List<BookACarDto>> getBookingsByUserId(@PathVariable Long userId){
        return ResponseEntity.ok(customerService.getBookingsByUserId(userId));
    }

    // Autos suchen
    @PostMapping("/car/search")
    public ResponseEntity<?> searchCar(SearchCarDto searchCarDto){
        return ResponseEntity.ok(customerService.searchCar(searchCarDto));
    }
}

