package com.example.YoungJu_Lee_Spring.repository;

import com.example.YoungJu_Lee_Spring.domain.PlayList;
import com.example.YoungJu_Lee_Spring.domain.PlayListSong;
import com.example.YoungJu_Lee_Spring.domain.Song;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class PlayListRepository {

    @PersistenceContext
    EntityManager em;

    public String save(PlayList playlist) {
        if(findByName(playlist.getName()) != null){
            throw new IllegalArgumentException("PlayList name already exists.");
        }
        em.persist(playlist);
        return playlist.getName();
    }

    public PlayList findByName(String name) {
        try {
            return em.createQuery("select p from PlayList p where p.name = :name", PlayList.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}

