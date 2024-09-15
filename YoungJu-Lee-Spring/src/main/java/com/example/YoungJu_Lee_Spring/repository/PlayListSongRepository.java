package com.example.YoungJu_Lee_Spring.repository;

import com.example.YoungJu_Lee_Spring.domain.PlayList;
import com.example.YoungJu_Lee_Spring.domain.PlayListSong;
import com.example.YoungJu_Lee_Spring.domain.Song;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class PlayListSongRepository {

    @PersistenceContext
    EntityManager em;

    public void save(PlayList playList, Song song) {
        PlayListSong playListSong = PlayListSong.builder()
                .playlist(playList)
                .song(song)
                .build();
        em.persist(playListSong);
    }


    public List<PlayListSong> getSongsInPlayList(PlayList playList) {
        return em.createQuery("select pls from PlayListSong pls where pls.playlist.id = :id", PlayListSong.class)
                .setParameter("id", playList.getId())
                .getResultList();
    }
}
