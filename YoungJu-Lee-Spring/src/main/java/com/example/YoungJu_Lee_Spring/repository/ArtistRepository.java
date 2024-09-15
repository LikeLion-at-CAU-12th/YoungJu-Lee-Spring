package com.example.YoungJu_Lee_Spring.repository;

import com.example.YoungJu_Lee_Spring.domain.Artist;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class ArtistRepository {

    @PersistenceContext
    EntityManager em;

    public String save(Artist artist) {
        em.persist(artist);
        return artist.getName();
    }
}
