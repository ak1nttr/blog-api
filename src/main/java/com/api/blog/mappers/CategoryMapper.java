package com.api.blog.mappers;

import com.api.blog.domain.PostStatus;
import com.api.blog.domain.dtos.CategoryDto;
import com.api.blog.domain.dtos.CreateCategoryRequest;
import com.api.blog.domain.entities.Category;
import com.api.blog.domain.entities.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface CategoryMapper {
    @Mapping(target = "postCount", source = "posts", qualifiedByName = "calculatePostCount")
    CategoryDto toDto(Category category);

    Category toEntity(CreateCategoryRequest request);

    @Named("calculatePostCount")
    default long calculatePostCount(List<Post> posts){
        if(posts == null){
            return 0;
        }
        return posts
                .stream()
                .filter(post -> PostStatus.PUBLISHED.equals(post.getStatus()))
                .count();
    }
}
