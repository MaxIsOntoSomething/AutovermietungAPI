package com.SalerProjekt.controller;

import com.SalerProjekt.dto.BookACarDto;
import com.SalerProjekt.dto.CarDto;
import com.SalerProjekt.dto.SearchCarDto;
import com.SalerProjekt.services.admin.AdminService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    // Auto hinzufügen für Admins
    @PostMapping("/car")
    public ResponseEntity<?> postCar(CarDto carDto) throws IOException {
        boolean success = adminService.postCar(carDto);
        if (success){
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // Alle Autos auflisten
    @GetMapping("/cars")
    public ResponseEntity<?> getAllCars(){
        return ResponseEntity.ok(adminService.getAllCars());
    }

    // Auto löschen
    @DeleteMapping("/car/{id}")
    public ResponseEntity<?> deleteCar(@PathVariable Long id){
        adminService.deleteCar(id);
        return ResponseEntity.ok(null);
    }

    // Auto nach ID suchen
    @GetMapping("/car/{id}")
    public ResponseEntity<CarDto> getCarById(@PathVariable Long id){
        CarDto carDto = adminService.getCarById(id);
        return ResponseEntity.ok(carDto);
    }

    @PutMapping("/car/{carId}")
    public ResponseEntity<Void> updateCar(@PathVariable Long carId, @ModelAttribute CarDto carDto) throws IOException {
        try {
            boolean success = adminService.updateCar(carId, carDto);

            if (success) {
                return ResponseEntity.status(HttpStatus.OK).build();
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/car/bookings")
    @Transactional
    public ResponseEntity<List<BookACarDto>> getBookings(){
        return ResponseEntity.ok(adminService.getBookings());
    }

    @GetMapping("/car/booking/{bookingId}/{status}")
    public ResponseEntity<?> changeBookingStatus(@PathVariable Long bookingId, @PathVariable String status){
        boolean success = adminService.changeBookingsStatus(bookingId, status);
        if (success){
            return ResponseEntity.ok(null);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/car/search")
    public ResponseEntity<?> searchCar(SearchCarDto searchCarDto){
        return ResponseEntity.ok(adminService.searchCar(searchCarDto));
    }
}
