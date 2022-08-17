package com.example.EjercicioSesion789SpringBoot.repository;

import com.example.EjercicioSesion789SpringBoot.entities.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LaptopRepository extends JpaRepository<Laptop, Long> {}
