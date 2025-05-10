package com.api.blog.services;


import com.api.blog.domain.entities.Tag;

import java.util.List;

public interface TagService {
    List<Tag> getTags();
}
