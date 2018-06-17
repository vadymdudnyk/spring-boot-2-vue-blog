package com.vdudnyk.blogbackend.post;

import com.vdudnyk.blogbackend.tag.Tag;
import com.vdudnyk.blogbackend.user.User;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class Post {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String content;

    @ManyToOne
    private User author;

    @ManyToMany
    private Set<Tag> tags;

}
