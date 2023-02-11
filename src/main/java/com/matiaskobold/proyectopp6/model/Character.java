package com.matiaskobold.proyectopp6.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

//Anotaciones de JPA para persistir esta clase en la DB
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@Entity
@Table(name = "characters")
public class Character {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column(nullable=false)
    private String name;

    @Column(nullable = false)
    private int age;

    @Column(nullable = false)
    private String race;

    @Column(nullable = false)
    private String player_username;

    @Column(nullable = false)
    private String player_discord;

    /*Consiste en:
    CascadeType.Persiste = Cuando creo un Character, si lo creo con una Address, la Address se crea tambien
    CascadeType.Remove = Si borro un Character, se borra la Address
    CascadeType.Merge = Si reattacheo un Character, cualquier cambio a la tabla de Address se mergea con los datos ya persistidos.
   */

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="homes_id", referencedColumnName ="id")
    private Home home;
/*
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "characterSet")
    private Set<Clan> clanSet = new HashSet<>();

    public Set<Clan> getClanSet() {
        return clanSet;
    }

    public void setClanSet(Set<Clan> clanSet) {
        this.clanSet = clanSet;
    }
*/
    public Home getHome() {
        return home;
    }

    public void setHome(Home home) {
        this.home = home;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPlayer_username() {
        return player_username;
    }

    public void setPlayer_username(String username) {
        this.player_username = username;
    }

    public String getPlayer_discord() {
        return player_discord;
    }

    public void setPlayer_discord(String email) {
        this.player_discord = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String gender) {
        this.race = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String full_name) {
        this.name = full_name;
    }

    public Character(String name, int age, String race, String player_username, String player_discord, Home home, Set<Clan> clanSet) {
        this.name = name;
        this.age = age;
        this.race = race;
        this.player_username = player_username;
        this.player_discord = player_discord;
        this.home = home;
        //this.clanSet = clanSet;
    }

    public Character(long id, String name, int age, String race, String player_username, String player_discord, Home home, Set<Clan> clanSet) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.race = race;
        this.player_username = player_username;
        this.player_discord = player_discord;
        this.home = home;
        //this.clanSet = clanSet;
    }

    public Character() {
    }



}
