package com.PlatMovie.controller;

import com.PlatMovie.Mapper.CategoryMapper;
import com.PlatMovie.controller.request.CategoryRequest;
import com.PlatMovie.controller.response.CategoryResponse;
import com.PlatMovie.entity.Category;
import com.PlatMovie.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/PlatMovie/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping()
    public ResponseEntity<List<CategoryResponse>> GetAllCategories(){
        List<CategoryResponse> categories = categoryService.findAll()
                .stream()
                .map(CategoryMapper::toCategoryResponse)
                .toList();
                return ResponseEntity.ok(categories);
    }
    @PostMapping
    public ResponseEntity<CategoryResponse> saveCategory(@Valid @RequestBody CategoryRequest request){
        Category newCategory = CategoryMapper.toCategory(request);
        Category savedCategory = categoryService.saveCategory(newCategory);
        return ResponseEntity.status(HttpStatus.CREATED).body(CategoryMapper.toCategoryResponse(savedCategory));
    }
    @GetMapping("/{id}")
    public  ResponseEntity<CategoryResponse> getCategoryById(@PathVariable long id){
        return  categoryService.getCategoryId(id)
                .map(category -> ResponseEntity.ok(CategoryMapper.toCategoryResponse(category)))
                .orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delCategoryById(@PathVariable long id) {
        return categoryService.getCategoryId(id)
                .map(category -> {
                    categoryService.delById(id);
                    return ResponseEntity.ok("O id: " + category.getId() + "foi deletado");

               })
                        .orElse(ResponseEntity.notFound().build());

    }
}


