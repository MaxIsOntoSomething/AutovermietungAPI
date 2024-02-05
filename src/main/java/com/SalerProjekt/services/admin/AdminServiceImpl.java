package com.SalerProjekt.services.admin;

import com.SalerProjekt.dto.CarDto;
import com.SalerProjekt.entity.Car;
import com.SalerProjekt.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final CarRepository carRepository;

    @Override
    public boolean postCar(CarDto carDto) throws IOException {
        try{
            Car car = new Car();
            car.setName(carDto.getName());
            car.setColor(carDto.getColor());
            car.setBrand(carDto.getBrand());
            car.setPrice(carDto.getPrice());
            car.setYear(carDto.getYear());
            car.setType(carDto.getType());
            car.setDescription(carDto.getDescription());
            car.setTransmission(carDto.getTransmission());
            car.setImage(carDto.getImage().getBytes());
            carRepository.save(car);
            return true;
        } catch (Exception e){
            return false;
        }
    }
    @Override
    public List<CarDto> getAllCars() {
        return carRepository.findAll().stream().map(Car::getCarDto).collect(Collectors.toList());
    }
}