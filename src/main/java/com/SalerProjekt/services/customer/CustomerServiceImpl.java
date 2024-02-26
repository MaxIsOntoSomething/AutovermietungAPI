package com.SalerProjekt.services.customer;

import com.SalerProjekt.dto.BookACarDto;
import com.SalerProjekt.dto.CarDto;
import com.SalerProjekt.entity.BookACar;
import com.SalerProjekt.entity.Car;
import com.SalerProjekt.entity.User;
import com.SalerProjekt.enums.BookCarStatus;
import com.SalerProjekt.repository.BookACarRepository;
import com.SalerProjekt.repository.CarRepository;
import com.SalerProjekt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CarRepository carRepository;
    private final UserRepository userRepository;
    private final BookACarRepository bookACarRepository;
    @Override
    public List<CarDto> getAllCars() {
        return carRepository.findAll().stream().map(Car::getCarDto).collect(Collectors.toList());
    }

    @Override
    public boolean bookACar(BookACarDto bookACarDto) {
        Optional<Car> optionalCar = carRepository.findById(bookACarDto.getCarId());
        Optional<User> optionalUser = userRepository.findById(bookACarDto.getUserId());
        if (optionalCar.isPresent() && optionalUser.isPresent()){
            Car existingCar = optionalCar.get();
            BookACar bookACar = new BookACar();
            bookACar.setCar(existingCar);
            bookACar.setUser(optionalUser.get());
            bookACar.setBookCarStatus(BookCarStatus.PENDING);
            long diffInMilliSeconds = bookACarDto.getToDate().getTime() - bookACarDto.getFromDate().getTime();
            long days = TimeUnit.MICROSECONDS.toDays(diffInMilliSeconds);
            bookACar.setDays(days);
            bookACar.setPrice(existingCar.getPrice() * days);

            bookACarRepository.save(bookACar);
            return true;
        }else {
            return false;
        }
    }
}
