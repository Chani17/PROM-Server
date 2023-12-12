package inu.thebite.toryaba.service.serviceImpl;

import inu.thebite.toryaba.entity.Category;
import inu.thebite.toryaba.model.image.AddCategoryRequest;
import inu.thebite.toryaba.repository.CategoryRepository;
import inu.thebite.toryaba.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional
    @Override
    public Category addCategory(AddCategoryRequest addCategoryRequest) {
        Category category = Category.createCategory(addCategoryRequest.getName());
        categoryRepository.save(category);
        return category;
    }

    @Override
    public List<Category> getCategoryList() {
        List<Category> categoryList = categoryRepository.findAll();
        return categoryList;
    }

    @Transactional
    @Override
    public void deleteCategory(Long categoryId) {
        if(categoryRepository.findById(categoryId).isPresent()) {
            categoryRepository.deleteById(categoryId);
        } else {
            throw new IllegalStateException("해당 category가 존재하지 않습니다.");
        }
    }
}
