package com.SalerProjekt.entity;

import com.SalerProjekt.dto.CarDto;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String brand;

    private String color;

    private String name;

    private String type;

    private String transmission;

    private String description;

    private Long price;

    private Date year;

    @Column(columnDefinition = "longblob")
    private byte[] image;

    public CarDto getCarDto() {
        CarDto carDto = new CarDto();
        carDto.setId(id);
        carDto.setName(name);
        carDto.setColor(color);
        carDto.setBrand(brand);
        carDto.setType(type);
        carDto.setPrice(price);
        carDto.setTransmission(transmission);
        carDto.setDescription(description);
        carDto.setYear(year);
        carDto.setReturnedImage(image);
        return carDto;

    }
}
