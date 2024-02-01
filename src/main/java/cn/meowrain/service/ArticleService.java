package cn.meowrain.service;

import cn.meowrain.pojo.Article;

import java.util.List;

public interface ArticleService {
    void postNewArticle(Article article);

    void updateArticle(Article article);

    Article getArticleDetailById(Integer id);

    List<Article> getAllArticle();

    void deleteArticleById(Integer id);
}
