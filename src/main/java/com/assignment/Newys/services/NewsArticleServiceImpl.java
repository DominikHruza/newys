package com.assignment.Newys.services;

import com.assignment.Newys.DTO.ArticleDto;
import com.assignment.Newys.models.NewsArticle;
import com.assignment.Newys.models.User;
import com.assignment.Newys.repository.NewsArticleRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class NewsArticleServiceImpl implements NewsArticleService {
    SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");

    NewsArticleRepository newsArticleRepository;

    public NewsArticleServiceImpl(NewsArticleRepository newsArticleRepository) {
        this.newsArticleRepository = newsArticleRepository;
    }

    @Override
    public List<NewsArticle> getAllArticles() {
        return null;
    }

    @Transactional
    @Override
    public void addNewArticle(ArticleDto articleDto, User user) {

        Date date = new Date(System.currentTimeMillis());
        NewsArticle article = new NewsArticle(
                articleDto.getHeader(),
                articleDto.getContent(),
                formatter.format(date)
        );

        article.setUser(user);
        newsArticleRepository.save(article);
    }
}
