package com.example.friend.service;

import com.example.friend.domain.Friend;
import com.example.friend.dto.FriendDTO;
import com.example.friend.repository.FriendRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
public class FriendServiceImplTests {

    @Autowired
    private FriendService friendService;

    @Autowired
    private FriendRepository friendRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Test
    public void testRegister() {
        FriendDTO friendDTO = FriendDTO.builder()
                .name("name1")
                .phone("phone1")
                .address("address1")
                .build();

        Long fno = friendService.register(friendDTO);
    }

    @Test
    public void testReadOne() {
        FriendDTO friendDTO = friendService.readOne(100L);
    }

    @Test
    public void testModify() {
        FriendDTO friendDTO = FriendDTO.builder()
                .fno(100L)
                .phone("번호 수정")
                .address("주소 수정")
                .build();
        friendService.modify(friendDTO);
    }

    @Test
    public void testRemove() {
        friendService.remove(85L);

    }
}