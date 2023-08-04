package com.example.friend.repository;

import com.example.friend.domain.Friend;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
class FriendRepositoryTests {

    @Autowired
    private FriendRepository friendRepository;

    @Test
    public void testInsert() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Friend friend = Friend.builder().
                    name("name" + i).
                    phone("phone" + i).
                    address("address" + i).
                    email("email"+i).
                    build(); // application.properties에 spring.jpa.show-sql=true 이게 있어야 콘솔에서 db 들어가는게 보여줌.

            Friend result = friendRepository.save(friend);
            log.info("bno  : " + result.getFno());
        });
    }

    @Test
    public void testRead() {


        Optional<Friend> id = friendRepository.findById(3L);

        Friend friend = id.orElseThrow();
        log.info(friend);
    }

    @Test
    public void testDelete() {

        Optional<Friend> id = friendRepository.findById(3L);
        Friend friend = id.orElseThrow();
        friendRepository.delete(friend);

    }

    @Test
    public void testModify() {
        Optional<Friend> id = friendRepository.findById(3L);
        Friend friend = id.orElseThrow();

        friend.change("phone 바꿈", "주소 바꿈");

        friendRepository.save(friend);


    }









    }