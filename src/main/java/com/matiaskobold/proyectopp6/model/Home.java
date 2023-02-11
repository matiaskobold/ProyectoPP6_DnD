package com.matiaskobold.proyectopp6.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@Entity
@Table(name = "homes")
public class Home {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String country;

    @OneToOne(mappedBy = "home")
    @JsonIgnore //me daba recursion error en la serializacion para devolver. porque character tiene home y home tiene character.
    private Character character;

    public Home(long id, String address, String city, String country, Character character) {
        this.id = id;
        this.address = address;
        this.city = city;
        this.country = country;
        this.character = character;
    }

    public Home(String address, String city, String country, Character character) {
        this.address = address;
        this.city = city;
        this.country = country;
        this.character = character;
    }

    public Home() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }


    public Character getCharacter() {
        return character;
    }


    public void setCharacter(Character character) {
        this.character = character;
    }
}
