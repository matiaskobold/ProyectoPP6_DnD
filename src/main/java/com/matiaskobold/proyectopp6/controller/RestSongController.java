package com.matiaskobold.proyectopp6.controller;

import com.matiaskobold.proyectopp6.model.Clan;
import com.matiaskobold.proyectopp6.model.Song;
import com.matiaskobold.proyectopp6.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class RestSongController {

    @Autowired
    private SongService songService;

    @GetMapping("/clans/{id}/songs")
    public ResponseEntity<List<Song>> getAllSongsByClanId(@PathVariable(value = "id") Long clanId) {
        return songService.getAllSongsByClanId(clanId);
    }

    @GetMapping("/songs/{id}")
    public ResponseEntity<Song> getSongById(@PathVariable(value = "id") Long id) {
        return songService.getSongById(id);
    }

    @PostMapping("/clans/{id}/songs")
    public ResponseEntity<Song> createSong(@PathVariable(value = "id") Long clanId, @Valid
                                           @RequestBody Song song) {
        return songService.createSong(clanId, song);
    }

    @PutMapping("/song/{id}")
    public Song updateSongById(@PathVariable(value="id") Long id, @Valid @RequestBody Song song){
        return songService.updateSongById(id, song);
    }

    @DeleteMapping("/song/{id}")
    public ResponseEntity<String> deleteSongById(@PathVariable(value="id") Long id){
        return songService.deleteSongById(id);
    }

    @DeleteMapping("/clans/{id}/songs")
    public ResponseEntity<?> deleteAllSongsByClanId(@PathVariable(value="id") Long id){
        return songService.deleteAllSongsByClanId(id);
    }
}
