package com.example.EjercicioSesion10_11_12SpringBoot.repository;

import com.example.EjercicioSesion10_11_12SpringBoot.entities.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LaptopRepository extends JpaRepository<Laptop, Long> {}
