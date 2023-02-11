package com.matiaskobold.proyectopp6.service;

import com.matiaskobold.proyectopp6.exception.ResourceNotFoundException;
import com.matiaskobold.proyectopp6.model.Clan;
import com.matiaskobold.proyectopp6.model.Song;
import com.matiaskobold.proyectopp6.repository.ClanRepository;
import com.matiaskobold.proyectopp6.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongService {

    @Autowired
    SongRepository songRepository;

    @Autowired
    ClanRepository clanRepository;

    public ResponseEntity<List<Song>> getAllSongsByClanId(Long clanId) {
        if (!clanRepository.existsById(clanId)){
            throw new ResourceNotFoundException("Not found clan with id"+clanId);
        }
        else{
            List<Song> songs = songRepository.findByClanId(clanId);
            return new ResponseEntity<>(songs, HttpStatus.OK);
        }
    }

    public ResponseEntity<Song> getSongById(Long id) {
        Song song = songRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Didn't found Song with Id: "+id));
        return new ResponseEntity<>(song, HttpStatus.OK);

    }


    public Song createSong(Long clanId, Song song) {
       return clanRepository.findById(clanId).map(clan -> {
           song.setClan(clan);
           return songRepository.save(song);
       }).orElseThrow(()->new ResourceNotFoundException("Clan id "+clanId+" not found"));
    }


    public Song updateSongById(Long id, Song song) {
        Song songUpd = songRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Song not found for Id: "+id));
        songUpd.setDescription(song.getDescription());
        songUpd.setClasification(song.getClasification());
        songUpd.setName(song.getName());
        songUpd.setParticipants(song.getParticipants());
        return songRepository.save(songUpd);
    }

    public ResponseEntity<HttpStatus> deleteSongById(Long id) {
        if (songRepository.existsById(id)){
            songRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }


    public ResponseEntity<?> deleteAllSongsByClanId(Long id) {
        if(!clanRepository.existsById(id)){
            return new ResponseEntity<>("Clan not found by Id: "+id, HttpStatus.NOT_FOUND);
        }
        else{
            songRepository.deleteByClanId(id);
            return new ResponseEntity<>("Deleted songs", HttpStatus.OK);
        }
    }
}
