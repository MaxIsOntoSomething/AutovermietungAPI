package com.SalerProjekt.repository;

import com.SalerProjekt.entity.BookACar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BookACarRepository extends JpaRepository<BookACar, Long> {
}
