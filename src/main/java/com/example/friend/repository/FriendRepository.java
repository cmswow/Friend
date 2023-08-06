package com.example.friend.repository;

import com.example.friend.domain.Friend;
import com.example.friend.repository.search.FriendSearch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendRepository extends JpaRepository<Friend, Long>, FriendSearch {


}
