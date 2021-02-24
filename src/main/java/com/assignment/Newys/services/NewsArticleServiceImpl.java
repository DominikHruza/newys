package com.assignment.Newys.services;

import com.assignment.Newys.DTO.ArticleDto;
import com.assignment.Newys.exceptions.NotFoundInDbException;
import com.assignment.Newys.models.NewsArticle;
import com.assignment.Newys.models.User;
import com.assignment.Newys.repository.NewsArticleRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
    public NewsArticle addNewArticle(ArticleDto articleDto, User user) {
        Date date = new Date(System.currentTimeMillis());
        NewsArticle article = new NewsArticle(
                articleDto.getHeader(),
                articleDto.getContent(),
                formatter.format(date)
        );
        article.setUser(user);
        return newsArticleRepository.save(article);
    }

    @Override
    @Transactional
    public NewsArticle updateArticle(ArticleDto articleDto, Long id) {
        Date date = new Date(System.currentTimeMillis());
        NewsArticle article = checkIfArticleExists(id);

        article.setHeader(articleDto.getHeader());
        article.setContent(article.getContent());
        article.setCreatedAt(formatter.format(date));

        return newsArticleRepository.save(article);
    }

    @Override
    @Transactional
    public void deleteArticle(Long id) {
        NewsArticle article = checkIfArticleExists(id);
        newsArticleRepository.delete(article);
    }

    private NewsArticle checkIfArticleExists(Long id){
        Optional<NewsArticle> optionalUser = newsArticleRepository.findById(id);
        if(!optionalUser.isPresent()){
            throw new NotFoundInDbException("Article with id " + id + " does not exists");
        }
        return optionalUser.get();
    }
}
