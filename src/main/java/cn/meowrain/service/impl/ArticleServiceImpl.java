package cn.meowrain.service.impl;

import cn.meowrain.mapper.ArticleMapper;
import cn.meowrain.pojo.Article;
import cn.meowrain.service.ArticleService;
import cn.meowrain.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    ArticleMapper articleMapper;

    @Override
    public void postNewArticle(Article article) {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id = (Integer)map.get("id");
        article.setCreateUser(id);
        articleMapper.postNewArticle(article);
    }

    @Override
    public void updateArticle(Article article) {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id = (Integer)map.get("id");
        article.setCreateUser(id);
        article.setUpdateTime(LocalDateTime.now());
        articleMapper.updateArticle(article);
    }

    @Override
    public Article getArticleDetailById(Integer id) {
        return articleMapper.getArticleDetailById(id);
    }

    @Override
    public List<Article> getAllArticle() {
        return articleMapper.getAllArticle();
    }

    @Override
    public void deleteArticleById(Integer id) {
        articleMapper.deleteArtcileById(id);
    }
}
