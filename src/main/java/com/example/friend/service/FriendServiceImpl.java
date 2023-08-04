package com.example.friend.service;

import com.example.friend.domain.Friend;
import com.example.friend.dto.FriendDTO;
import com.example.friend.repository.FriendRepository;
import groovy.util.logging.Log4j2;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class FriendServiceImpl implements FriendService{

    private final ModelMapper modelMapper;
    private final FriendRepository friendRepository;
    @Override
    public Long register(FriendDTO friendDTO) {
        Friend friend = modelMapper.map(friendDTO, Friend.class);
        Long fno = friendRepository.save(friend).getFno();
        //-----------DB저장 ------------------>저장된 Bno값을 반환

        return fno;
    }

    @Override
    public FriendDTO readOne(Long fno) {
        Optional<Friend> result = friendRepository.findById(fno);
        Friend friend = result.orElseThrow();
        FriendDTO friendDTO = modelMapper.map(friend, FriendDTO.class);

        return friendDTO;
    }

    @Override
    public void modify(FriendDTO friendDTO) {

        Optional<Friend> result = friendRepository.findById(friendDTO.getFno());

        Friend friend = result.orElseThrow();
        friend.change(friendDTO.getPhone(), friendDTO.getAddress());
        friendRepository.save(friend); // save --> insert,update 두 기능 수행

    }

    @Override
    public void remove(Long fno) {
        friendRepository.deleteById(fno);
    }


}
