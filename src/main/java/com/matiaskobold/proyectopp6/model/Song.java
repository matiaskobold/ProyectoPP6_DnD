package com.matiaskobold.proyectopp6.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.Optional;

@Entity
@Table(name = "songs")
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable=false)
    private String name;

    @Column(nullable=false)
    private String description;

    @Column(nullable=false)
    private String clasification;

    @Column(nullable=false)
    @Min(value = 1)
    private int participants;

    @ManyToOne
    @JoinColumn(name = "clans_id")
    @OnDelete(action= OnDeleteAction.CASCADE)
    @JsonIgnore
    private Clan clan;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getClasification() {
        return clasification;
    }

    public void setClasification(String clasification) {
        this.clasification = clasification;
    }

    public int getParticipants() {
        return participants;
    }

    public void setParticipants(int participants) {
        this.participants = participants;
    }

    public Clan getClan() {
        return clan;
    }

    public void setClan(Clan clan) {
        this.clan = clan;
    }

    public Song(long id, String name, String description, String clasification, int participants, Clan clan) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.clasification = clasification;
        this.participants = participants;
        this.clan = clan;
    }

    public Song(String name, String description, String clasification, int participants, Clan clan) {
        this.name = name;
        this.description = description;
        this.clasification = clasification;
        this.participants = participants;
        this.clan = clan;
    }

    public Song() {
    }
}
