package com.vdudnyk.blogbackend.tag;

import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TagService {
    private TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public Set<Tag> saveTagsAndGet(List<String> stringTags) {
        return stringTags
                .stream()
                .map(stringTag -> {
                    Optional<Tag> dbTag = tagRepository.findByName(stringTag);
                    return dbTag.orElseGet(() -> tagRepository.save(new Tag(stringTag)));
                })
                .collect(Collectors.toSet());
    }
}
