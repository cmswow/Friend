package com.example.friend.controller;


import com.example.friend.domain.Friend;
import com.example.friend.dto.FriendDTO;
import com.example.friend.dto.PageRequestDTO;
import com.example.friend.dto.PageResponseDTO;
import com.example.friend.service.FriendService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

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

        @GetMapping("/register")
        public void registerGET() {

        }

        @PostMapping("/register")
        public String register(@Valid FriendDTO friendDTO, BindingResult bindingResult, RedirectAttributes rttr) {

            if (bindingResult.hasErrors()) {
                rttr.addFlashAttribute("errors", bindingResult.getAllErrors());
                return "redirect:/register";
            }

        Long fno = friendService.register(friendDTO);
            rttr.addFlashAttribute("result",fno);


            return "redirect:/home";
        }

        @GetMapping({"/read","/modify"})
        public void read(Long fno,PageRequestDTO pageRequestDTO,Model model){

            FriendDTO friendDTO = friendService.readOne(fno);
            model.addAttribute("dto",friendDTO);
        }



}
