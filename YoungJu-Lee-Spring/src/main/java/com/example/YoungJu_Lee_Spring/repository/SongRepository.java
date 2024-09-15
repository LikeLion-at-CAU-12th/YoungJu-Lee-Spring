package com.example.YoungJu_Lee_Spring.repository;

import com.example.YoungJu_Lee_Spring.domain.Song;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class SongRepository {

    @PersistenceContext
    EntityManager em;

    public String save(Song song) {
        em.persist(song);
        return song.getTitle();
    }

    public List<Song> findByTitle(String title) {
        try {
            return em.createQuery("select s from Song s where s.title like :title", Song.class)
                    .setParameter("title", "%" + title + "%")
                    .getResultList();
        } catch (NoResultException e) {
            System.out.println("result not found.");
            return null;
        }
    }

    public Song findById(String id) {
        return em.find(Song.class, id);
    }

}
