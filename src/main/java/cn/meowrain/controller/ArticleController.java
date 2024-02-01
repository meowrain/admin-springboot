package cn.meowrain.controller;

import cn.meowrain.pojo.Article;
import cn.meowrain.pojo.Result;
import cn.meowrain.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    ArticleService articleService;
    @GetMapping("/list")
    public Result<List<Article>> list(){
        return Result.success(articleService.getAllArticle());
    }
    @GetMapping("/detail")
    public Result<Article> getArticleDetail(@RequestParam Integer id){
        return Result.success(articleService.getArticleDetailById(id));
    }
    @PostMapping
    public Result postNewArticle(@RequestBody Article article){
        articleService.postNewArticle(article);
        return Result.success("插入成功！");
    }

    @PutMapping
    public Result updateArticle(@RequestBody Article article){
        articleService.updateArticle(article);
        return Result.success("修改成功");
    }

    @DeleteMapping("/delete")
    public Result deleteArticleById(@RequestParam Integer id){
        articleService.deleteArticleById(id);
        return Result.success("删除成功");
    }
}
