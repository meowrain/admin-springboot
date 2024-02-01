package cn.meowrain.service.impl;

import cn.meowrain.mapper.CategoryMapper;
import cn.meowrain.pojo.Category;
import cn.meowrain.service.CategoryService;
import cn.meowrain.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryMapper  categoryMapper;
    @Override
    public List<Category> getCategories() {
        return categoryMapper.getCategories();
    }

    @Override
    public void addCategory(@RequestBody Category category) {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id = (Integer)map.get("id");
        System.out.println(id);
        category.setCreateUser(id);
        categoryMapper.insertCategory(category);
    }

    @Override
    public void updateCategory(Category category) {
        category.setUpdateTime(LocalDateTime.now());
        categoryMapper.updateCategory(category);
    }

    @Override
    public Category getCategoryById(Integer id) {
        return categoryMapper.getCategoryById(id);
    }

    @Override
    public void deleteCategoryById(Integer id) {
        categoryMapper.deleteCategoryById(id);
    }
}
