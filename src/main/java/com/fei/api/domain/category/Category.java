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
    private String typeNumber;

    @Column
    private String typeName;

    @Column
    private String name;

    @Column
    private String img;

    @Column
    private Long index;

    @Builder
    public Category(
        String typeNumber,
        String typeName,
        String name,
        String img,
        Long index
    ) {
        this.typeNumber = typeNumber;
        this.typeName = typeName;
        this.name = name;
        this.img = img;
        this.index = index;
    }
}
