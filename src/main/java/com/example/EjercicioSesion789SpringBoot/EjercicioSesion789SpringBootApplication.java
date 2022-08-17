package com.example.EjercicioSesion789SpringBoot;

import com.example.EjercicioSesion789SpringBoot.entities.Laptop;
import com.example.EjercicioSesion789SpringBoot.repository.LaptopRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class EjercicioSesion789SpringBootApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(EjercicioSesion789SpringBootApplication.class, args);

		LaptopRepository repository = context.getBean(LaptopRepository.class);

		//Creamos un laptop

		Laptop laptop = new Laptop(null, "ASUS", "TR500", 599.99);
		Laptop laptop1 = new Laptop(null, "HP", "EliteBook", 750.00);

		//Almcacenar laptops
		System.out.println("Numero de laptos en base de datos son: " + repository.findAll().size());

		repository.save(laptop);
		repository.save(laptop1);

		//Recuperar los laptos de la BBDD

		System.out.println("Numero de laptos en base de datos son: " + repository.findAll().size());


		//Borrar laptop de la BBDD

		//repository.deleteById(1L);

		System.out.println("Numero de laptos en base de datos son: " + repository.findAll().size());







	}

}
