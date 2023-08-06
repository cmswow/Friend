package com.example.friend.repository.search;

import com.example.friend.domain.Friend;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FriendSearch {

    Page<Friend> search1(Pageable pageable);

    Page<Friend> searchAll(String[] types, String keyword, Pageable pageable);
}
