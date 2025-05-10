package com.api.blog.controllers;


import com.api.blog.domain.dtos.TagResponse;
import com.api.blog.domain.entities.Tag;
import com.api.blog.mappers.TagMapper;
import com.api.blog.services.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/tags")
@RequiredArgsConstructor
public class TagController {
    private final TagService tagService;
    private final TagMapper tagMapper;


    @GetMapping
    public ResponseEntity<List<TagResponse>> getAllTags() {
        List<Tag> tags = tagService.getTags();
        List<TagResponse> response = tags.stream()
                .map(tagMapper::toTagResponse)
                .toList();
        return ResponseEntity.ok(response);
    }


}
