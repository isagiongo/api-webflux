package com.isagiongo.webflux.repositories;

import com.isagiongo.webflux.documents.Playlist;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface PlaylistRepository extends ReactiveMongoRepository <Playlist, String> {
}
