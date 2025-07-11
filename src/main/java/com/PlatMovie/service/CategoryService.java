package com.PlatMovie.service;

import com.PlatMovie.entity.Category;
import com.PlatMovie.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository repository;

    public List<Category> findAll(){
        return repository.findAll();
    }

    public Category saveCategory(Category category){
        return repository.save(category);
    }
    public Optional<Category> getCategoryId(long id){
        return repository.findById(id);
    }
    public void delById(long id){
        repository.deleteById(id);
    }

}
