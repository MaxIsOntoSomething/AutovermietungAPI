package com.SalerProjekt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.SalerProjekt.entity.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {


}
