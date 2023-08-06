package com.example.friend.service;

import com.example.friend.dto.FriendDTO;
import com.example.friend.dto.PageRequestDTO;
import com.example.friend.dto.PageResponseDTO;

public interface FriendService {

    Long register(FriendDTO friendDTO);

    FriendDTO readOne(Long fno);

    void modify(FriendDTO friendDTO);

    void remove(Long fno);

    PageResponseDTO<FriendDTO> list(PageRequestDTO pageRequestDTO);
}
