package com.example.EjercicioSesion10_11_12SpringBoot.controller;


import com.example.EjercicioSesion10_11_12SpringBoot.entities.Laptop;
import com.example.EjercicioSesion10_11_12SpringBoot.repository.LaptopRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Optional;

@RestController
public class LaptopController {

    //Nos creamos un atributo de tipo Laptop

    private LaptopRepository laptopRepository;
    private final Logger log = LoggerFactory.getLogger(LaptopController.class);

    //Contructor

    public LaptopController(LaptopRepository laptopRepository) {
        this.laptopRepository = laptopRepository;
    }


    //CRUD sobre la entidad Laptop


    //Buscar laptop en la BBDD (Lista de Laptops)

    /*
     * http://localhost:8080/api/laptop
     * @return
     *
     * */
    @GetMapping("/api/laptop")
    @ApiOperation("Buscar todos los laptop de la base de datos")
    public List<Laptop> findAll() {

        //Recuperar y devolver los laptop de la BBDD
        return laptopRepository.findAll();

    }

    /*
     *
     * http://localhost:8080/api/laptop/1
     *
     * */

    //Buscar un laptop en la BBDD según su ID
    @GetMapping("/api/laptop/{id}")
    @ApiOperation("Buscar un laptop por la clave primaria en la base de datos")

    public ResponseEntity<Laptop> findOneById(@PathVariable Long id) {
        Optional<Laptop> laptopOpt = laptopRepository.findById(id);
        if (laptopOpt.isPresent()) {
            return ResponseEntity.ok().body(laptopOpt.get());
        } else {
            //Para que nos cree respuesta 404 de not found
            return ResponseEntity.notFound().build();
        }
    }

    //Crear un nuevo Laptop en la BBDD

    /**
     * Método POST, no colisiona con findAll() porrque son diferentes métodos
     * http://localhost:8080/api/laptop
     */
    @PostMapping("/api/laptop")
    @ApiOperation("Crear un laptop en la base de datos")

    public ResponseEntity<Laptop> createLaptop(@RequestBody Laptop laptop) {

        //Guardar el laptop recibido por parámetros en la BBDD
        if (laptop.getId() != null) {//quiere decir que existe el id y por tanto no es una creaciòn
            log.warn("trying to create a laptop with id");
            return ResponseEntity.badRequest().build();
        }

        Laptop result = laptopRepository.save(laptop);
        return ResponseEntity.ok(result);
    }


    /***
     * Actualizar la BBDD
     *
     *
     */
    @PutMapping("/api/laptop")
    @ApiOperation("Actualizar un laptop por la clave primaria")

    public ResponseEntity<Laptop> updateLaptop(@RequestBody Laptop laptop) {

        //Guardar el laptop recibido


        if (laptop.getId() == null) {//Si no tiene id si que es una creación
            log.warn("trying to update a non laptop");

            return ResponseEntity.badRequest().build();
        }

        if (!laptopRepository.existsById(laptop.getId())) {

            log.warn("Trying to update a non existent laptop");

            return ResponseEntity.notFound().build();

        }

        Laptop result = laptopRepository.save(laptop);
        return ResponseEntity.ok(result);


    }

    /**
     * Borrar laptop de la BBDD
     */
    @ApiIgnore
    @DeleteMapping("/api/laptop/{id}")
    @ApiOperation("Borrar un laptop por la clave primaria")

    public ResponseEntity<Laptop> deleteLaptop(@PathVariable Long id) {
        if (!laptopRepository.existsById(id)) {
            log.warn("Trying to delete  a non existent laptop");

            return ResponseEntity.notFound().build();
        }

        laptopRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @ApiIgnore
    @DeleteMapping("/api/laptop")
    @ApiOperation("Borrar todos los laptop de la base de datos")

    public ResponseEntity<Laptop> deleteAllLaptop() {
        log.info("REST Resquest to delete all laptop");
        laptopRepository.deleteAll();
        return ResponseEntity.noContent().build();

    }


}
