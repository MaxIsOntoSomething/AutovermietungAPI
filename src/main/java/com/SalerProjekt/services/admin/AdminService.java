package com.SalerProjekt.services.admin;

import com.SalerProjekt.dto.BookACarDto;
import com.SalerProjekt.dto.CarDto;
import com.SalerProjekt.dto.CarDtoListDto;
import com.SalerProjekt.dto.SearchCarDto;

import java.io.IOException;
import java.util.List;

public interface AdminService {

    boolean postCar(CarDto carDto) throws IOException;

    List<CarDto> getAllCars();

    void deleteCar(Long id);

    CarDto getCarById(Long id);

    boolean updateCar(Long carId, CarDto carDto) throws IOException;

    List<BookACarDto> getBookings();

    boolean changeBookingsStatus(Long bookingId, String status);

    CarDtoListDto searchCar(SearchCarDto searchCarDto);
}
