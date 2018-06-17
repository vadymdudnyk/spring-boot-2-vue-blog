package com.vdudnyk.blogbackend.tag;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Tag {

    @Id
    @GeneratedValue
    private Long id;
    private String name;


    public Tag(String name) {
        this.name = name;
    }
}
