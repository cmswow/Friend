package com.example.friend.controller;


import com.example.friend.dto.FriendDTO;
import com.example.friend.dto.PageRequestDTO;
import com.example.friend.dto.PageResponseDTO;
import com.example.friend.service.FriendService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
@Log4j2
@RequiredArgsConstructor
public class FriendController {

        private final FriendService friendService;

        @GetMapping("/home")
        public void home(PageRequestDTO pageRequestDTO, Model model){
            PageResponseDTO<FriendDTO> responseDTO = friendService.list(pageRequestDTO);
            log.info(responseDTO);
            model.addAttribute("responseDTO",responseDTO);
        }

}
