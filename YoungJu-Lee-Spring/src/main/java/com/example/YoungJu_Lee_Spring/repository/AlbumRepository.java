package com.example.YoungJu_Lee_Spring.repository;

import com.example.YoungJu_Lee_Spring.domain.Album;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class AlbumRepository {

    @PersistenceContext
    EntityManager em;

    public String save(Album album) {
        em.persist(album);
        return album.getTitle();
    }
}
