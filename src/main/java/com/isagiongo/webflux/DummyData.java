package com.isagiongo.webflux;

import com.isagiongo.webflux.documents.Playlist;
import com.isagiongo.webflux.repositories.PlaylistRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.UUID;

@Component
public class DummyData implements CommandLineRunner {

    private final PlaylistRepository playlistRepository;

    DummyData(PlaylistRepository playlistRepository) {
        this.playlistRepository = playlistRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        playlistRepository.deleteAll()
                .thenMany(
                        Flux.just("Api Rest Spring Boot", "Deploy de uma aplicacao Java no IBM Cloud", "Java8", "GitHub", "Spring Security", "Web Service RestFull")
                            .map(nome -> new Playlist(UUID.randomUUID().toString(), nome))
                            .flatMap(playlistRepository::save))
                        .subscribe(System.out::println);
    }
}
