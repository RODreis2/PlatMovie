package com.PlatMovie.Mapper;

import com.PlatMovie.controller.request.CategoryRequest;
import com.PlatMovie.controller.response.CategoryResponse;
import com.PlatMovie.entity.Category;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CategoryMapper {

    public static Category toCategory(CategoryRequest categoryRequest){
        return Category
                .builder()
                .name(categoryRequest.name())
                .build();
    }
    public static CategoryResponse toCategoryResponse(Category category){
        return CategoryResponse
                .builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

}
