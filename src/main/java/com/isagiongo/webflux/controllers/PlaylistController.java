package com.isagiongo.webflux.controllers;

import com.isagiongo.webflux.documents.Playlist;
import com.isagiongo.webflux.services.PlaylistService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

//@RestController
public class PlaylistController {

    private final PlaylistService playlistService;

    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @GetMapping("/playlists")
    public Flux<Playlist> listaPlaylists() {
        return playlistService.findAll();
    }

    @GetMapping("/playlists/{id}")
    public Mono<Playlist> buscaPlaylist(@PathVariable String id) {
        return playlistService.findById(id);
    }

    @PostMapping("/playlists")
    public Mono<Playlist> salvaPlaylist(@RequestBody Playlist playlist) {
        return playlistService.save(playlist);
    }
}
