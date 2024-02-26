package com.SalerProjekt.services.customer;

import com.SalerProjekt.dto.BookACarDto;
import com.SalerProjekt.dto.CarDto;

import java.util.List;

public interface CustomerService {

    List<CarDto> getAllCars();

    boolean bookACar(BookACarDto bookACarDto);
}
