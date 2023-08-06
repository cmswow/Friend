package com.example.friend.service;

import com.example.friend.domain.Friend;
import com.example.friend.dto.FriendDTO;
import com.example.friend.dto.PageRequestDTO;
import com.example.friend.dto.PageResponseDTO;
import com.example.friend.repository.FriendRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Override
    public PageResponseDTO<FriendDTO> list(PageRequestDTO pageRequestDTO) {
        String[] types = pageRequestDTO.getTypes();
        String keyword = pageRequestDTO.getKeyword();
        Pageable pageable = pageRequestDTO.getPageable("fno");

        Page<Friend> result = friendRepository.searchAll(types,keyword,pageable);

        log.info("-----------------------------------------------");
        log.info("aaa getTotalPages: " + result.getTotalPages());
        log.info("aaa getSize: " + result.getSize());
        log.info("aaa getTotalElements: " + result.getTotalElements());
        result.getContent().forEach(friend -> log.info(friend));
        log.info("------------------------------------------------");




        List<FriendDTO> dtoList = result.getContent().stream()
                .map(friend -> modelMapper.map(friend,FriendDTO.class)).collect(Collectors.toList());

        PageResponseDTO<FriendDTO> pageResponseDTO =
                new PageResponseDTO<>(pageRequestDTO, dtoList,(int) result.getTotalElements());

        return pageResponseDTO;



//        return PageResponseDTO.<BoardDTO>withAll()

//                .pageRequestDTO(pageRequestDTO)
//                .dtoList(dtoList)
//                .total((int)result.getTotalElements())
//                .build();
    }



}
