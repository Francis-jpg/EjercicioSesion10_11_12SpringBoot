package com.example.EjercicioSesion789SpringBoot.entities;


import javax.persistence.*;

@Entity
@Table(name = "ordenadores")
public class Laptop {


    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private String model;
    private Double price;


    //Constructor

    public Laptop() {
    }

    public Laptop(Long id, String brand, String model, Double price) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.price = price;
    }

    // Getter y Setter


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
