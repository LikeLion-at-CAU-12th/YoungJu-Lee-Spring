package com.example.YoungJu_Lee_Spring.repository;

import com.example.YoungJu_Lee_Spring.domain.Album;
import com.example.YoungJu_Lee_Spring.domain.Artist;
import com.example.YoungJu_Lee_Spring.domain.Song;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.Scanner;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SongRepositoryTest {

    @Autowired
    private SongRepository songRepository;

    @Autowired
    EntityManager em;

    @Test
    @Transactional()
    @Rollback(false)
    public void testSong(){
        // 인스턴스 생성
        Artist artist = Artist.builder()
                .name("사자")
                .build();

        Album album = Album.builder()
                .title("사자모음집")
                .artist(artist)
                .build();

        Song song = Song.builder()
                .title("멋쟁이사자")
                .artist(artist)
                .album(album)
                .build();

        Song song2 = Song.builder()
                .title("사자어흥")
                .artist(artist)
                .album(album)
                .build();

        String savedSong = songRepository.save(song);
        String savedSong2 = songRepository.save(song2);
        System.out.println(savedSong);
        System.out.println(savedSong2);

        // 노래 검색 테스트
        //Scanner input = new Scanner(System.in);
        //System.out.println("Enter title of the song: ");
        //String title = input.nextLine();

        // 사용자 입력 관련해서 인텔리제이 오류로 일단 이렇게 처리
        List<Song> result = songRepository.findByTitle("사자");

        System.out.println("results : ");
        for (Song s : result) {
            System.out.println("Title: " + s.getTitle() + ", Album: " + s.getAlbum().getTitle() + ", Artist: " + s.getArtist().getName());
        }

    }
}