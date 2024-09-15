package com.example.YoungJu_Lee_Spring.repository;

import com.example.YoungJu_Lee_Spring.domain.PlayList;
import com.example.YoungJu_Lee_Spring.domain.PlayListSong;
import com.example.YoungJu_Lee_Spring.domain.Song;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PlayListRepositoryTest {

    @Autowired
    private PlayListRepository playListRepository;

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private PlayListSongRepository playListSongRepository;

    @Autowired
    EntityManager em;

    @Test
    @Transactional
    @Rollback(false)
    public void testPlayList(){
        // 플레이리스트 생성
        PlayList myPlayList = PlayList.builder()
                .name("멋사할 때 듣기 좋은 플레이리스트")
                .description("과제 1분 컷 가능")
                .build();

        playListRepository.save(myPlayList);
        
        // 플레이리스트에 노래 추가
        List<Song> result = songRepository.findByTitle("사자");
        if(!result.isEmpty()){
            playListSongRepository.save(myPlayList, result.get(0));
        }

        // 플레이리스트에 담긴 노래 출력
        List<PlayListSong> songs = playListSongRepository.getSongsInPlayList(myPlayList);
        for (PlayListSong playListSong : songs){
            System.out.println(playListSong.getSong().getTitle());
        }
    }
}