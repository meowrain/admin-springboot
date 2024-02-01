package cn.meowrain.service;

import cn.meowrain.pojo.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getCategories();

    void addCategory(Category category);

    void updateCategory(Category category);

    Category getCategoryById(Integer id);

    void deleteCategoryById(Integer id);
}
