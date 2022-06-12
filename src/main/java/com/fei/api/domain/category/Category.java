package com.fei.api.domain.category;


import com.fei.api.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Category extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String type;

    @Column
    private String krName;

    @Column
    private String enName;

    @Column
    private Long index;

    @Builder
    public Category(
        String type,
        String krName,
        String enName,
        Long index
    ) {
        this.type = type;
        this.krName = krName;
        this.enName = enName;
        this.index = index;
    }
}
