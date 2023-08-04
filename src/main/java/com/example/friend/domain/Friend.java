package com.example.friend.domain;


import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Friend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fno;

    @Column(nullable = false) // length 안쓰면 기본 255자 , 업데이트 허용 no
    private String name;
    @Column(length = 14)
    private String phone;
    @Column(length = 1000)
    private String address;
    @Column(length = 50)
    private String email;

    public void change(String phone, String address) {
        this.phone = phone;
        this.address = address;
    }


}
