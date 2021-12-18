package com.fei.api.domain.user;


import com.fei.api.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String userId;

    @Column
    private String pw;

    @Column
    private int age;

    @Column
    private String gender;

    @Column
    private String name;

    @Column
    private String role;

    @Builder
    public User(
        String userId,
        String pw,
        int age,
        String gender,
        String name,
        String role
    ) {
        this.userId = userId;
        this.pw = pw;
        this.age = age;
        this.gender = gender;
        this.name = name;
        this.role = role;
    }
}
