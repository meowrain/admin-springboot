package cn.meowrain.controller;

import cn.meowrain.pojo.Category;
import cn.meowrain.pojo.Result;
import cn.meowrain.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @PostMapping
    public Result add(@RequestBody @Validated Category category){
        System.out.println(category.toString());
        categoryService.addCategory(category);
        return Result.success("插入成功！");
    }

    @GetMapping
    public Result<List<Category>> getCategories(){
        return Result.success(categoryService.getCategories());
    }

    @PutMapping
    public Result updateCategory(@RequestBody Category category){
        categoryService.updateCategory(category);
        return Result.success("修改成功！");
    }

    @GetMapping("/detail")
    public Result<Category> getCategoryById(@RequestParam Integer id){
        return Result.success(categoryService.getCategoryById(id));
    }

    @DeleteMapping("/delete")
    public Result deleteCategoryById(@RequestParam Integer id){
        categoryService.deleteCategoryById(id);
        return Result.success("删除成功！");
    }
}
