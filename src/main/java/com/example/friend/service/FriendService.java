package com.example.friend.service;

import com.example.friend.dto.FriendDTO;

public interface FriendService {

    Long register(FriendDTO friendDTO);

    FriendDTO readOne(Long fno);

    void modify(FriendDTO friendDTO);

    void remove(Long fno);
}
