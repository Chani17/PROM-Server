package inu.thebite.toryaba.service;

import inu.thebite.toryaba.entity.Category;
import inu.thebite.toryaba.model.image.AddCategoryRequest;

import java.util.List;

public interface CategoryService {
    Category addCategory(AddCategoryRequest addCategoryRequest);

    List<Category> getCategoryList();

    void deleteCategory(Long categoryId);
}
