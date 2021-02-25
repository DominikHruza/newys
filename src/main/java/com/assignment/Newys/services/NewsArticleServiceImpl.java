package com.assignment.Newys.services;

import com.assignment.Newys.DTO.ArticleDto;
import com.assignment.Newys.DTO.UserDto;
import com.assignment.Newys.exceptions.DuplicateResourceEntryException;
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
import java.util.stream.Collectors;

@Service
public class NewsArticleServiceImpl implements NewsArticleService {
    SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");

    NewsArticleRepository newsArticleRepository;

    public NewsArticleServiceImpl(NewsArticleRepository newsArticleRepository) {
        this.newsArticleRepository = newsArticleRepository;
    }

    @Override
    public  List<ArticleDto> getAllArticles() {
        List<NewsArticle> articles = newsArticleRepository.findAll();
        List<ArticleDto> articleDtos = articles.stream()
                .map(article -> ArticleDto.ConvertToDto(article))
                .collect(Collectors.toList());

        return articleDtos;
    }

    @Override
    public ArticleDto getArticleById(Long id) {
        NewsArticle article = checkIfArticleExists(id);
        return ArticleDto.ConvertToDto(article);
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

    @Override
    @Transactional
    public NewsArticle saveLike(Long articleId, User user) {
        NewsArticle article = checkIfArticleExists(articleId);
        boolean isAdded = article.addLike(user);
        if(!isAdded){
            throw new DuplicateResourceEntryException(
                    "Article with id: " + articleId + " already liked by user: " + user.getUsername());
        }

        return newsArticleRepository.save(article);
    }

    @Override
    @Transactional
    public NewsArticle deleteLike(Long articleId, User user) {
        NewsArticle article = checkIfArticleExists(articleId);
        boolean isRemoved = article.getLikes().remove(user);

        if(!isRemoved){
            throw new NotFoundInDbException(
                    "Article with id: " + articleId + " not liked by user: " + user.getUsername());
        }
        return newsArticleRepository.save(article);
    }

    @Override
    public List<UserDto> getAllLikes(Long articleId) {
       NewsArticle article = checkIfArticleExists(articleId);
       List<UserDto> userDtos = article
               .getLikes()
               .stream()
               .map(user -> UserDto.convertToDto(user))
               .collect(Collectors.toList());
       return userDtos;
    }

    private NewsArticle checkIfArticleExists(Long id){
        Optional<NewsArticle> optionalUser = newsArticleRepository.findById(id);
        return optionalUser.orElseThrow(() ->
                new NotFoundInDbException("Article with id " + id + " does not exists"));
    }
}
