package com.SalerProjekt.services.customer;

import com.SalerProjekt.dto.BookACarDto;
import com.SalerProjekt.dto.CarDto;
import com.SalerProjekt.dto.CarDtoListDto;
import com.SalerProjekt.dto.SearchCarDto;

import java.util.List;

public interface CustomerService {

    List<CarDto> getAllCars();

    boolean bookACar(BookACarDto bookACarDto, Long carId);

    CarDto getCarById(Long carId);

    List<BookACarDto> getBookingsByUserId(Long userId);

    CarDtoListDto searchCar(SearchCarDto searchCarDto);
}
