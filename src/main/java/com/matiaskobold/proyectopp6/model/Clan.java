package com.matiaskobold.proyectopp6.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "clans")
public class Clan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String main_language;

    @Column(nullable = false)
    private String operations_base;

    @Column
    private String title;
/*
Desarmo el many to many, sino en los tests tengo que probar esto también y es repetir programación al pedo.
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "clan_has_character",
            joinColumns = {@JoinColumn(name = "clans_id")},
            inverseJoinColumns = {@JoinColumn(name = "characters_id")})
    private Set<Character> characterSet = new HashSet<>();

    public Set<Character> getCharacterSet() {
        return characterSet;
    }

    public void setCharacterSet(Set<Character> characterSet) {
        this.characterSet = characterSet;
    }

    //estos son los que me hacen el ida y vuelta
    public void addCharacter(Character character) {
        this.characterSet.add(character);
        character.getClanSet().add(this);
    }

    public void removeCharacter(long characterId) {
        Character character = this.characterSet.stream().filter(character1 -> character1.getId() == characterId).findFirst().orElse(null);
        if (character!=null){
            this.characterSet.remove(character);
            character.getClanSet().remove(this);
        }
    }
*/

    public Clan(long id, String name, String description, String main_language, String operations_base, String title/*, Set<Character> characterSet*/) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.main_language = main_language;
        this.operations_base = operations_base;
        this.title = title;
        //this.characterSet = characterSet;
    }

    public Clan(String name, String description, String main_language, String operations_base, String title/*, Set<Character> characterSet*/) {
        this.name = name;
        this.description = description;
        this.main_language = main_language;
        this.operations_base = operations_base;
        this.title = title;
        //this.characterSet = characterSet;
    }

    public Clan() {
    }

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

    public String getMain_language() {
        return main_language;
    }

    public void setMain_language(String main_language) {
        this.main_language = main_language;
    }

    public String getOperations_base() {
        return operations_base;
    }

    public void setOperations_base(String operations_base) {
        this.operations_base = operations_base;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
