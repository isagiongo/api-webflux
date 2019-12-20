package com.isagiongo.webflux;

import com.isagiongo.webflux.documents.Playlist;
import com.isagiongo.webflux.services.PlaylistService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyInserters.fromPublisher;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
public class PlaylistHandler {

    private final PlaylistService playlistService;

    public PlaylistHandler(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    public Mono<ServerResponse> findAll(ServerRequest serverRequest) {
        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(playlistService.findAll(), Playlist.class);
    }

    public Mono<ServerResponse> findById(ServerRequest serverRequest) {
        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(playlistService.findById(serverRequest.pathVariable("id")), Playlist.class);
    }

    public Mono<ServerResponse> save(ServerRequest serverRequest) {
        final Mono<Playlist> playlistMono = serverRequest.bodyToMono(Playlist.class);
        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(fromPublisher(playlistMono.flatMap(playlistService::save), Playlist.class));
    }
}
