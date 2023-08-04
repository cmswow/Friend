package com.example.friend.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FriendDTO {

    private Long fno;

    private String name;

    private String phone;

    private String address;

    private String email;

}
